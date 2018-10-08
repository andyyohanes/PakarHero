package com.radicallabsinc.pakarhero.ui.setting.certification;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.CertificationRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.CertificationResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class CertificationPresenter<V extends CertificationMvpView> extends BasePresenter<V> implements CertificationMvpPresenter<V> {

    @Inject
    public CertificationPresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        CertificationRequest certificationRequest = new CertificationRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(),getDataManager().getCurrentUserId());
        getCompositeDisposable().add(getDataManager()
                .getCerts(certificationRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CertificationResponse>() {
                    @Override
                    public void accept(CertificationResponse certificationResponse) throws Exception {
                        if (certificationResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().getCertification(certificationResponse.getData());
                        } else if(certificationResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
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
    public void deleteItem(CertificationResponse.CertificationData data, final int position) {
        getMvpView().showLoading();
        CertificationRequest certificationRequest = new CertificationRequest(data.getCertId(),getDataManager().getCurrentUserId(), getDataManager().getAccessToken());
        getCompositeDisposable().add(getDataManager()
                .delCert(certificationRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CertificationResponse>() {
                    @Override
                    public void accept(CertificationResponse certificationResponse) throws Exception {
                        if (certificationResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().onSuccessDeleteItem(position);
                        } else if(certificationResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
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
    public void saveItem(String period, String desc) {
        getMvpView().showLoading();
        CertificationRequest certificationRequest = new CertificationRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(),period,desc);
        getCompositeDisposable().add(getDataManager()
                .saveCert(certificationRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CertificationResponse>() {
                    @Override
                    public void accept(CertificationResponse certificationResponse) throws Exception {
                        if (certificationResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().onSuccessSaveItem();
                        } else if(certificationResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
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
