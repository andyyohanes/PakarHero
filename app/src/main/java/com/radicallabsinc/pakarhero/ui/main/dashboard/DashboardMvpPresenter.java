package com.radicallabsinc.pakarhero.ui.main.dashboard;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface DashboardMvpPresenter<V extends DashboardMvpView> extends MvpPresenter<V> {
    void getServiceFeeUSD();

    void getServiceFeeIDR();
}
