package com.radicallabsinc.pakarhero.ui.main.dashboard.my_case;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface MyCaseMvpPresenter<V extends MyCaseMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
