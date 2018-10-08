package com.radicallabsinc.pakarhero.ui.main.expertise.expert;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.ExpertRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ExpertPresenter<V extends ExpertMvpView> extends BasePresenter<V> implements ExpertMvpPresenter<V> {

    @Inject
    public ExpertPresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(String expertiseCode) {
        getMvpView().showLoading();
        ExpertRequest expertRequest = new ExpertRequest(expertiseCode);
        getCompositeDisposable().add(getDataManager()
                .getExperts(expertRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExpertResponse>() {
                    @Override
                    public void accept(ExpertResponse expertResponse) throws Exception {
                        if (expertResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().updateExpert(expertResponse.getData());
                        } else {
                            getMvpView().showNotFound();
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
