package com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail;

import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.ui.base.MvpView;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ExpertDetailPresenter<V extends MvpView> extends BasePresenter<V> implements ExpertDetailMvpPresenter<V> {

    @Inject
    public ExpertDetailPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
