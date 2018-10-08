package com.radicallabsinc.pakarhero.ui.main;

import android.support.v4.app.Fragment;

import com.radicallabsinc.pakarhero.ui.base.MvpView;

public interface MainMvpView extends MvpView{

    void authorizedView();

    void unAuthorizedView();

    void refreshLayout();

    void changeTabLayoutExpert();

    void resetTabLayout();
}
