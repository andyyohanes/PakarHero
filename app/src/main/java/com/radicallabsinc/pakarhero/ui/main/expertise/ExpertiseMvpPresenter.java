package com.radicallabsinc.pakarhero.ui.main.expertise;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;
import com.radicallabsinc.pakarhero.ui.main.MainMvpView;

public interface ExpertiseMvpPresenter<V extends ExpertiseMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
