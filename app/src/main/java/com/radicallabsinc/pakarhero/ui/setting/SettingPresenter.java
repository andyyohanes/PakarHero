package com.radicallabsinc.pakarhero.ui.setting;

import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingPresenter<V extends SettingMvpView> extends BasePresenter<V> implements SettingMvpPresenter<V> {
    @Inject
    public SettingPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
