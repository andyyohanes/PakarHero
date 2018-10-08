package com.radicallabsinc.pakarhero.ui.auth.verify;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.VerifyRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.BaseResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class VerifyPresenter<V extends VerifyMvpView> extends BasePresenter<V> implements VerifyMvpPresenter<V>  {
    @Inject
    public VerifyPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void sendNewCode(String username, String phone) {
        getMvpView().showLoading();
        VerifyRequest verifyRequest = new VerifyRequest(username,phone);
        getCompositeDisposable().add(getDataManager()
                .newCode(verifyRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse response) throws Exception {
                        if (response.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            //getMvpView().showSuccessDialog();
                        } else {
                            getMvpView().onError(response.getStatus());
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
    public void verifyCode(String username, String countryCode, String phone, String code) {
        getMvpView().showLoading();
        VerifyRequest verifyRequest = new VerifyRequest(username,countryCode,phone,code);
        getCompositeDisposable().add(getDataManager()
                .verify(verifyRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse response) throws Exception {
                        if (response.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            //getMvpView().showSuccessDialog();
                        } else {
                            getMvpView().onError(response.getStatus());
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
