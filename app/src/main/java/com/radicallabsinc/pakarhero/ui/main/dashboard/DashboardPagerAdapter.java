package com.radicallabsinc.pakarhero.ui.main.dashboard;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case.CustomerCaseFragment;
import com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case.CustomerCaseRootFragment;
import com.radicallabsinc.pakarhero.ui.main.dashboard.my_case.MyCaseFragment;
import com.radicallabsinc.pakarhero.ui.main.dashboard.my_case.MyCaseRootFragment;

public class DashboardPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public DashboardPagerAdapter(FragmentManager fm){
        super(fm);
        tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        final Fragment result;
        switch (position) {
            case 0:
                result = new CustomerCaseRootFragment();
                break;
            case 1:
                result = new MyCaseRootFragment();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Customer Case";
            case 1:
                return "My Case";
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    void setTabCount(int tabCount) {
        this.tabCount = tabCount;
    }
}
