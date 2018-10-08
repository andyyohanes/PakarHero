package com.radicallabsinc.pakarhero.ui.main;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    boolean checkAuthorization();

    void doLogout();

    void getExpertiseData();

    void getLovData();
}
