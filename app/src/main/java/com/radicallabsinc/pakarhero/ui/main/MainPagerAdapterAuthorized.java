package com.radicallabsinc.pakarhero.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case.CustomerCaseFragment;
import com.radicallabsinc.pakarhero.ui.main.dashboard.my_case.MyCaseFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.ExpertiseFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.RootFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.ExpertFragment;

public class MainPagerAdapterAuthorized extends FragmentStatePagerAdapter {
    private int tabCount;

    public MainPagerAdapterAuthorized(FragmentManager fm){
        super(fm);
        tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RootFragment();
            case 1:
                return null;
            case 2:
                return CustomerCaseFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    void setTabCount(int tabCount){
        this.tabCount = tabCount;
    }
}
