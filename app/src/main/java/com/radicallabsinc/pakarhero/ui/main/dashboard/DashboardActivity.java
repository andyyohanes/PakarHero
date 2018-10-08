package com.radicallabsinc.pakarhero.ui.main.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends BaseActivity implements DashboardMvpView{

    @Inject
    DashboardMvpPresenter<DashboardMvpView> mPresenter;

    @Inject
    DashboardPagerAdapter mPagerAdapter;

    @BindView(R.id.mToolbar)
    Toolbar mToolbar;

    @BindView(R.id.vpDashboard)
    ViewPager vpDashboard;

    @BindView(R.id.tabsCases)
    TabLayout tabsCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        mPresenter.getServiceFeeUSD();
        mPresenter.getServiceFeeIDR();
        mToolbar.setTitle("Dashboard");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mPagerAdapter.setTabCount(2);
        vpDashboard.setAdapter(mPagerAdapter);
        vpDashboard.setOffscreenPageLimit(1);
        vpDashboard.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabsCases));

        tabsCases.setupWithViewPager(vpDashboard);

        tabsCases.getTabAt(0).setTag("next");
        tabsCases.getTabAt(1).setTag("next");

        tabsCases.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getTag().equals("back")) {
                    tab.setTag("next");
                    onBackPressed();
                }
            }
        });
    }

    @Override
    public void changeTabLayout(int position) {
        tabsCases.getTabAt(position).setTag("back");
    }

    @Override
    public void refreshLayout() {
        showLoading();
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
        hideLoading();
    }
}
