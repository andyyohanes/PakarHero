package com.radicallabsinc.pakarhero.ui.auth;

import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AuthPresenter<V extends AuthMvpView> extends BasePresenter<V>
        implements AuthMvpPresenter<V> {

    @Inject
    public AuthPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
