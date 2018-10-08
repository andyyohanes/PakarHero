package com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.di.component.ActivityComponent;
import com.radicallabsinc.pakarhero.ui.base.BaseFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review.ReviewActivity;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpertDetailFragment extends BaseFragment implements ExpertDetailMvpView {

    @Inject
    ExpertDetailMvpPresenter<ExpertDetailMvpView> mPresenter;

    @Inject
    ExpertDetailCertificationAdapter mCertificationAdapter;

    @Inject
    ExpertDetailWorkHistoryAdapter mWorkHistoryAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManagerCertification;

    @Inject
    LinearLayoutManager mLinearLayoutManagerWorkHistory;

    private ExpertResponse.ExpertData expertData;
    private SkillResponse.SkillData skillData;

    @BindView(R.id.llRatings)
    LinearLayout llRatings;

    @BindView(R.id.ivExpertImage)
    ImageView ivExpertImage;

    @BindView(R.id.tvExpertName)
    TextView tvExpertName;

    @BindView(R.id.rbExpert)
    RatingBar rbExpert;

    @BindView(R.id.tvCountRatings)
    TextView tvCountRatings;

    @BindView(R.id.tvPricePerSession)
    TextView tvPricePerSession;

    @BindView(R.id.ivExpertSkill)
    ImageView ivExpertSkill;

    @BindView(R.id.tvSkillDesc)
    TextView tvSkillDesc;

    @BindView(R.id.rvCertification)
    RecyclerView rvCertification;

    @BindView(R.id.rvWorkHistory)
    RecyclerView rvWorkHistory;

    @BindView(R.id.llCertification)
    LinearLayout llCertification;

    @BindView(R.id.llWorkHistory)
    LinearLayout llWorkHistory;

    public static ExpertDetailFragment newInstance(ExpertResponse.ExpertData expertData, Integer skillPosition){
        Bundle args = new Bundle();
        args.putSerializable("expertData", expertData);
        args.putInt("skillPosition",skillPosition);
        ExpertDetailFragment fragment = new ExpertDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expert_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if(component!=null){
            Integer skillPosition = getArguments().getInt("skillPosition");
            expertData = (ExpertResponse.ExpertData) getArguments().getSerializable("expertData");
            skillData = expertData.getExpertSkills().get(skillPosition);
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
//            mAdapter.setCallback(this,expertiseCode);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        tvExpertName.setText(expertData.getExpertFirstName());
        Picasso.with(view.getContext()).load(expertData.getExpertImg()).transform(new CircleTransform()).into(ivExpertImage);
        if(expertData.getExpertCountRatings()>0)
            rbExpert.setRating(expertData.getExpertSumRatings()/expertData.getExpertCountRatings());
        tvCountRatings.setText("("+expertData.getExpertCountRatings()+" reviews)");
        if(skillData.getSessionLength()>1)
            tvPricePerSession.setText(skillData.getCurrency()+ " " + skillData.getPricePerSession() + " / "
                    + skillData.getSessionLength() + " " + skillData.getSessionUnit());
        else
            tvPricePerSession.setText(skillData.getCurrency()+ " " + skillData.getPricePerSession() + " / "
                    + skillData.getSessionUnit());
        if(skillData.getSkillImg()!=null)
            Picasso.with(view.getContext()).load(skillData.getSkillImg()).into(ivExpertSkill);
        else
            Picasso.with(view.getContext()).load(expertData.getExpertImg()).into(ivExpertSkill);
        if(expertData.getExpertDesc()!=null)
            tvSkillDesc.setText(expertData.getExpertDesc());
        else
            tvSkillDesc.setText("No Description");

        if(expertData.getExpertCerts()==null) {
            llCertification.setVisibility(View.GONE);
        } else {
            mCertificationAdapter.addItems(expertData.getExpertCerts());

            rvCertification.setLayoutManager(mLinearLayoutManagerCertification);
            rvCertification.setItemAnimator(new DefaultItemAnimator());
            rvCertification.setAdapter(mCertificationAdapter);
        }

        if(expertData.getExpertResumes()==null) {
            llWorkHistory.setVisibility(View.GONE);
        } else {
            mWorkHistoryAdapter.addItems(expertData.getExpertResumes());
            rvWorkHistory.setLayoutManager(mLinearLayoutManagerWorkHistory);
            rvWorkHistory.setItemAnimator(new DefaultItemAnimator());
            rvWorkHistory.setAdapter(mWorkHistoryAdapter);
        }

        llRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                intent.putExtra("expertId",expertData.getExpertId());
                intent.putExtra("expertImg",expertData.getExpertImg());
                intent.putExtra("expertName",expertData.getExpertFirstName());
                startActivity(intent);
            }
        });
    }
}
