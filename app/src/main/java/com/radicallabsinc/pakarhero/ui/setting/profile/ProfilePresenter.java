package com.radicallabsinc.pakarhero.ui.setting.profile;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.LogoutRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.ProfileRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.BaseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.LoginResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ProfilePresenter<V extends ProfileMvpView> extends BasePresenter<V> implements ProfileMvpPresenter<V> {
    @Inject
    public ProfilePresenter(DataManager dataManager,
                                  SchedulerProvider schedulerProvider,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        LogoutRequest logoutRequest = new LogoutRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken());
        getCompositeDisposable().add(getDataManager()
                .getProfile(logoutRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        if (loginResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().getProfile(loginResponse.getData());
                        } else if(loginResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
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
    public void saveProfile(String firstName, String lastName, String countryCode, String phone, String desc, String languages, String paypalEmail, String dokuBankActName, String dokuBankActNumber, String dokuBankName, String dokuBranch, String img, String imgType) {
        getMvpView().showLoading();
        ProfileRequest profileRequest = new ProfileRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), getDataManager().getCurrentUserName(), getDataManager().getCurrentUserPassword(), firstName, lastName, countryCode, phone, desc, languages, paypalEmail, dokuBankActName, dokuBankActNumber, dokuBankName, dokuBranch, img, imgType);
        getCompositeDisposable().add(getDataManager()
                .saveProfile(profileRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        if (baseResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().showMessage("Profile saved");
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
