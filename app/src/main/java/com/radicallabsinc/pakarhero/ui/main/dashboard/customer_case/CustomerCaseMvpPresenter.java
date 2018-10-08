package com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface CustomerCaseMvpPresenter<V extends CustomerCaseMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
