package com.radicallabsinc.pakarhero.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.radicallabsinc.pakarhero.ui.main.expertise.ExpertiseFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.RootFragment;
import com.radicallabsinc.pakarhero.ui.main.expertise.expert.ExpertFragment;

public class MainPagerAdapterUnauthorized extends FragmentStatePagerAdapter {
    private int tabCount;

    public MainPagerAdapterUnauthorized(FragmentManager fm){
        super(fm);
        tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RootFragment();
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
