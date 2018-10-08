package com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface ReviewMvpPresenter<V extends ReviewMvpView> extends MvpPresenter<V> {
    void onViewPrepared(Long expertId);
}
