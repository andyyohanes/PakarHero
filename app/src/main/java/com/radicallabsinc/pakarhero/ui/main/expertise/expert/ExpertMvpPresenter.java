package com.radicallabsinc.pakarhero.ui.main.expertise.expert;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface ExpertMvpPresenter<V extends ExpertMvpView> extends MvpPresenter<V> {

    void onViewPrepared(String expertiseCode);
}
