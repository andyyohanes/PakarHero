package com.radicallabsinc.pakarhero.ui.main.dashboard.my_case;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.CaseResponse;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.main.dashboard.DashboardActivity;
import com.radicallabsinc.pakarhero.ui.main.dashboard.case_detail.CaseDetailFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCaseFragment extends BaseFragment implements MyCaseMvpView,MyCaseAdapter.Callback{

    @Inject
    MyCaseMvpPresenter<MyCaseMvpView> mPresenter;

    @Inject
    MyCaseAdapter mAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @BindView(R.id.rvExpertise)
    RecyclerView rvContent;

    public static MyCaseFragment newInstance(){
        Bundle args = new Bundle();
        MyCaseFragment fragment = new MyCaseFragment();
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
        rvContent.setLayoutManager(mLinearLayoutManager);
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rvContent.setAdapter(mAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void updateCase(List<CaseResponse.CaseData> activeCaseList, List<CaseResponse.CaseData> inactiveCaseList) {
        List<CaseResponse.CaseData> caseList = new ArrayList<>();
        List<String> caseTypeList = new ArrayList<>();
        List<Integer> caseTypeSize = new ArrayList<>();
        if(activeCaseList.size()!=0)
            caseTypeList.add("Active Case");
        if(inactiveCaseList.size()!=0)
            caseTypeList.add("Inactive Case");
        caseList.addAll(activeCaseList);
        caseList.addAll(inactiveCaseList);
        caseTypeSize.add(activeCaseList.size());
        caseTypeSize.add(inactiveCaseList.size());
        mAdapter.addItems(caseList,caseTypeList, caseTypeSize);
    }

    @Override
    public void onClickItem(CaseResponse.CaseData data) {
        Toast.makeText(getContext(), "Case# " + data.getCaseId(), Toast.LENGTH_SHORT).show();
        showFragment(CaseDetailFragment.newInstance(data,"customer"));
        ((DashboardActivity)getActivity()).changeTabLayout(1);
    }

    @Override
    public void showFragment(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flMainContainerMyCase, fragment, TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
