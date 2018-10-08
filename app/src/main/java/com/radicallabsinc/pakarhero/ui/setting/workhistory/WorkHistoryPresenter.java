package com.radicallabsinc.pakarhero.ui.setting.workhistory;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.WorkHistoryRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.WorkHistoryResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class WorkHistoryPresenter<V extends WorkHistoryMvpView> extends BasePresenter<V> implements WorkHistoryMvpPresenter<V> {
    @Inject
    public WorkHistoryPresenter(DataManager dataManager,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        WorkHistoryRequest workHistoryRequest = new WorkHistoryRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(),String.valueOf(getDataManager().getCurrentUserId()));
        getCompositeDisposable().add(getDataManager()
                .getWorkHistory(workHistoryRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<WorkHistoryResponse>() {
                    @Override
                    public void accept(WorkHistoryResponse workHistoryResponse) throws Exception {
                        if (workHistoryResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().getWorkHistory(workHistoryResponse.getData());
                        } else if(workHistoryResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
                            getDataManager().setUserAsLoggedOut();
                            getMvpView().openActivityOnTokenExpire();
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

    @Override
    public void deleteItem(WorkHistoryResponse.WorkHistoryData data, final int position) {
        getMvpView().showLoading();
        WorkHistoryRequest workHistoryRequest = new WorkHistoryRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), data.getResumeId());
        getCompositeDisposable().add(getDataManager()
                .delWorkHistory(workHistoryRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<WorkHistoryResponse>() {
                    @Override
                    public void accept(WorkHistoryResponse workHistoryResponse) throws Exception {
                        if (workHistoryResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().onSuccessDeleteItem(position);
                        } else if(workHistoryResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
                            getDataManager().setUserAsLoggedOut();
                            getMvpView().goToMainActivity();
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

    @Override
    public void saveItem(String company, String period, String desc) {
        getMvpView().showLoading();
        WorkHistoryRequest workHistoryRequest = new WorkHistoryRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(),company,period,desc);
        getCompositeDisposable().add(getDataManager()
                .saveWorkHistory(workHistoryRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<WorkHistoryResponse>() {
                    @Override
                    public void accept(WorkHistoryResponse workHistoryResponse) throws Exception {
                        if (workHistoryResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().onSuccessSaveItem();
                        } else if(workHistoryResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
                            getDataManager().setUserAsLoggedOut();
                            getMvpView().goToMainActivity();
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
