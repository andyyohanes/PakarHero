package com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.network.model.response.ReviewResponse;
import com.radicallabsinc.pakarhero.ui.base.BaseActivity;
import com.radicallabsinc.pakarhero.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends BaseActivity implements ReviewMvpView{

    @Inject
    ReviewMvpPresenter<ReviewMvpView> mPresenter;

    @Inject
    ReviewAdapter mAdapter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivExpertImage)
    ImageView ivExpertImage;
    @BindView(R.id.tvExpertName)
    TextView tvExpertName;
    @BindView(R.id.rvContent)
    RecyclerView rvReview;

    Long expertId;
    String expertImg, expertName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        expertId = getIntent().getLongExtra("expertId",0);
        expertImg = getIntent().getStringExtra("expertImg");
        expertName = getIntent().getStringExtra("expertName");
        setUp();
    }

    @Override
    protected void setUp() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Picasso.with(getBaseContext()).load(expertImg).transform(new CircleTransform()).into(ivExpertImage);
        tvExpertName.setText(expertName);

        rvReview.setLayoutManager(mLinearLayoutManager);
        rvReview.setItemAnimator(new DefaultItemAnimator());
        rvReview.addItemDecoration(new DividerItemDecoration(getBaseContext(),DividerItemDecoration.VERTICAL));
        rvReview.setAdapter(mAdapter);

        mPresenter.onViewPrepared(expertId);
    }

    @Override
    public void updateReview(List<ReviewResponse.ReviewData> reviewList) {
        mAdapter.addItems(reviewList);
    }
}
