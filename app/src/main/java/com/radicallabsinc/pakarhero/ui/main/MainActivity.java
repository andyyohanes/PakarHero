package com.radicallabsinc.pakarhero.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.ui.auth.AuthActivity;
import com.radicallabsinc.pakarhero.ui.base.BaseActivity;
import com.radicallabsinc.pakarhero.ui.main.dashboard.DashboardActivity;
import com.radicallabsinc.pakarhero.ui.setting.SettingActivity;
import com.wanderingcan.persistentsearch.PersistentSearchView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    MainPagerAdapterUnauthorized mAdapter;

    @Inject
    MainPagerAdapterAuthorized mAdapterAuthorized;

    @BindView(R.id.vpMain)
    ViewPager vpMain;

    @BindView(R.id.tabsBeforeLogin)
    TabLayout tlUnauthorized;

    @BindView(R.id.tabsAfterLogin)
    TabLayout tlAuthorized;

    @BindView(R.id.mToolbar)
    Toolbar mToolbar;

    @BindView(R.id.sbMain)
    PersistentSearchView sbMain;

    public boolean authorized = false;
    public static Activity activity;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        authorized = mPresenter.checkAuthorization();
        setSupportActionBar(mToolbar);
        tlAuthorized.getTabAt(0).setTag("next");
        tlUnauthorized.getTabAt(0).setTag("next");

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void authorizedView() {
        tlAuthorized.setVisibility(View.VISIBLE);
        tlUnauthorized.setVisibility(View.GONE);
        mAdapterAuthorized.setTabCount(1);

        vpMain.setAdapter(mAdapterAuthorized);
        vpMain.setOffscreenPageLimit(1);
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlAuthorized));

        tlAuthorized.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1) {
                    tlAuthorized.getTabAt(0).select();
                    startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0 && tab.getTag().equals("back")){
                    onBackPressed();
                }
            }
        });
    }

    @Override
    public void unAuthorizedView() {
        tlUnauthorized.setVisibility(View.VISIBLE);
        tlAuthorized.setVisibility(View.GONE);
        mAdapter.setTabCount(1);

        vpMain.setAdapter(mAdapter);
        vpMain.setOffscreenPageLimit(1);
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlUnauthorized));

        tlUnauthorized.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tlUnauthorized.getTabAt(0).select();
                if(tab.getPosition()==2) {
                    startActivity(new Intent(MainActivity.this, AuthActivity.class));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0 && tab.getTag().equals("back")){
                    onBackPressed();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(authorized) {
            getMenuInflater().inflate(R.menu.menu_main,menu);
            return true;
        } else
            return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile_menu:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                return true;
            case R.id.logout_menu:
                mPresenter.doLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void changeTabLayoutExpert() {
        View view = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.tab_tutor, null, false);
        if(authorized) {
            tlAuthorized.getTabAt(0).setTag("back");
            tlAuthorized.getTabAt(0).setCustomView((LinearLayout)view.findViewById(R.id.tabTutor));
        } else {
            tlUnauthorized.getTabAt(0).setTag("back");
            tlUnauthorized.getTabAt(0).setCustomView((LinearLayout)view.findViewById(R.id.tabTutor));
        }
    }

    @Override
    public void resetTabLayout() {
        if(authorized) {
            tlAuthorized.getTabAt(0).setTag("next");
            tlAuthorized.getTabAt(0).setCustomView((LinearLayout)findViewById(R.id.tabExpertise));
        } else {
            tlUnauthorized.getTabAt(0).setTag("next");
            tlUnauthorized.getTabAt(0).setCustomView((LinearLayout)findViewById(R.id.tabExpertise));
        }
    }
}
