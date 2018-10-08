package com.radicallabsinc.pakarhero.ui.main.expertise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.main.MainActivity;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.ExpertFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpertiseFragment extends BaseFragment implements ExpertiseMvpView,ExpertiseAdapter.Callback {

    @Inject
    ExpertiseMvpPresenter<ExpertiseMvpView> mPresenter;

    @Inject
    ExpertiseAdapter mAdapter;

    @BindView(R.id.rvExpertise)
    RecyclerView rvExpertise;

    public static ExpertiseFragment newInstance(){
        Bundle args = new Bundle();
        ExpertiseFragment fragment = new ExpertiseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expertise, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        rvExpertise.setLayoutManager(new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false));
        rvExpertise.setItemAnimator(new DefaultItemAnimator());
        rvExpertise.setAdapter(mAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void updateExpertise(List<ExpertiseResponse.ExpertiseData> expertiseList) {
        mAdapter.addItems(expertiseList);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onClickItem(String expertiseCode) {
        showFragment(ExpertFragment.newInstance(expertiseCode));
    }

    @Override
    public void showFragment(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flMainContainer, fragment, TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        ((MainActivity)getContext()).changeTabLayoutExpert();
    }
}
