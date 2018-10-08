package com.radicallabsinc.pakarhero.ui.main.dashboard;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.ServiceFeeRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.ServiceFeeResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class DashboardPresenter<V extends DashboardMvpView> extends BasePresenter<V> implements DashboardMvpPresenter<V> {

    @Inject
    public DashboardPresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getServiceFeeUSD() {
        ServiceFeeRequest serviceFeeRequest = new ServiceFeeRequest(getDataManager().getCurrentUserId(),getDataManager().getAccessToken(),"USD");
        getCompositeDisposable().add(getDataManager()
                .getServiceFee(serviceFeeRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ServiceFeeResponse>() {
                    @Override
                    public void accept(ServiceFeeResponse serviceFeeResponse) throws Exception {
                        if (serviceFeeResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            AppConstants.serviceFeeMap.put("USD",serviceFeeResponse.getData().getServiceFee());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void getServiceFeeIDR() {
        ServiceFeeRequest serviceFeeRequest = new ServiceFeeRequest(getDataManager().getCurrentUserId(),getDataManager().getAccessToken(),"IDR");
        getCompositeDisposable().add(getDataManager()
                .getServiceFee(serviceFeeRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ServiceFeeResponse>() {
                    @Override
                    public void accept(ServiceFeeResponse serviceFeeResponse) throws Exception {
                        if (serviceFeeResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            AppConstants.serviceFeeMap.put("IDR",serviceFeeResponse.getData().getServiceFee());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
