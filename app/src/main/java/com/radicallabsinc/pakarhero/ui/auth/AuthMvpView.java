package com.radicallabsinc.pakarhero.ui.auth;

import android.support.v4.app.Fragment;

import com.radicallabsinc.pakarhero.ui.base.MvpView;

public interface AuthMvpView extends MvpView {

    void showFragment(Fragment fragment);

    void backStackFragment();

    void removeCurrentFragment();

    void setToolbarTitle(int resId);

    void enableNavigationIcon();

    void disableNavigationIcon();

    void endActivity();
}
