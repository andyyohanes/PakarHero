package com.radicallabsinc.pakarhero.ui.setting;

import android.support.v4.app.Fragment;

import com.radicallabsinc.pakarhero.ui.base.MvpView;

public interface SettingMvpView extends MvpView {

    void showFragment(Fragment fragment);

    void removeCurrentFragment();

    void enableNavigationIcon();

    void endActivity();

}
