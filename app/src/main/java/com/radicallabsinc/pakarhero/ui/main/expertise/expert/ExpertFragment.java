package com.radicallabsinc.pakarhero.ui.main.expertise.expert;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertResponse;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.main.MainActivity;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.ExpertDetailFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpertFragment extends BaseFragment implements ExpertMvpView, ExpertAdapter.Callback {

    @Inject
    ExpertMvpPresenter<ExpertMvpView> mPresenter;

    @Inject
    ExpertAdapter mAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @BindView(R.id.rvExpertise)
    RecyclerView rvExpert;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    private String expertiseCode;

    private List<ExpertResponse.ExpertData> expertDataList = new ArrayList<>();
    private ArrayList<Integer> skillPosition = new ArrayList<>();

    public static ExpertFragment newInstance(String expertiseCode){
        Bundle args = new Bundle();
        args.putString("expertiseCode",expertiseCode);
        ExpertFragment fragment = new ExpertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expertise, container, false);
        ActivityComponent component = getActivityComponent();
        if(component!=null){
            expertiseCode = getArguments().getString("expertiseCode");
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mAdapter.setCallback(this,expertiseCode);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        rvExpert.setLayoutManager(mLinearLayoutManager);
        rvExpert.setItemAnimator(new DefaultItemAnimator());
        rvExpert.setAdapter(mAdapter);

        mPresenter.onViewPrepared(expertiseCode);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
        ((MainActivity)getContext()).resetTabLayout();
    }

    @Override
    public void updateExpert(List<ExpertResponse.ExpertData> expertList) {
        for(int i = 0; i < expertList.size();i++){
            for(int j = 0;j < expertList.get(i).getExpertSkills().size();j++){
                if(expertList.get(i).getExpertSkills().get(j).getExpertiseCode().equalsIgnoreCase(expertiseCode)){
                    expertDataList.add(expertList.get(i));
                    skillPosition.add(j);
                }
            }
        }
        mAdapter.addItems(expertDataList, skillPosition);
    }

    @Override
    public void showNotFound() {
        rvExpert.setVisibility(View.GONE);
        tvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClicked(Integer position, Integer skillPosition) {
        showFragment(ExpertDetailFragment.newInstance(expertDataList.get(position),skillPosition));
    }

    @Override
    public void showFragment(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flMainContainer, fragment, TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
