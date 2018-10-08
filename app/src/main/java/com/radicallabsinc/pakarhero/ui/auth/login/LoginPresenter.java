package com.radicallabsinc.pakarhero.ui.auth.login;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.JoinRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.LoginRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.BaseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.CheckNameResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.LoginResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onActionClicked() {
        if(getMvpView().isPasswordVisible()){
            checkLogin(getMvpView().getUsername(),getMvpView().getPassword());
        } else {
            getMvpView().showLoading();
            LoginRequest loginRequest = new LoginRequest(getMvpView().getUsername());
            getCompositeDisposable().add(getDataManager()
                    .checkUsername(loginRequest)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<CheckNameResponse>() {
                        @Override
                        public void accept(CheckNameResponse response) throws Exception {
                            if (response.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                                if(!response.getData().isUserNameAvailable()){
                                    getMvpView().changePasswordLayoutVisibility(true);
                                    getMvpView().setFocusToPassword();
                                } else {
                                    getMvpView().onJoinClicked(getMvpView().getUsername());
                                }
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

    @Override
    public void checkLogin(String username, final String password) {
        getMvpView().showLoading();
        final String pushToken = getMvpView().getPushToken();
        LoginRequest request = new LoginRequest(username,password,pushToken);
        getCompositeDisposable().add(getDataManager()
                .login(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        if (loginResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            //getMvpView().updateExpertise(expertiseResponse.getData());
                            doLogin(loginResponse.getData().getUserId(),
                                    password,
                                    pushToken,
                                    loginResponse.getData().getUserName(),
                                    loginResponse.getData().getAuthToken(),
                                    loginResponse.getData().getUserType(),
                                    loginResponse.getData().getUserLocale(),
                                    loginResponse.getData().getUserImg());
                        } else {
                            getMvpView().onErrorShow(loginResponse.getStatus());
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
    public void doLogin(Long userId, String password, String pushToken, String userName, String authToken, String userType, String userLocale, String userPic) {
        getDataManager().updateUserInfo(
                authToken,
                userId,
                password,
                pushToken,
                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                userName,
                userType,
                userLocale,
                userPic);
        getMvpView().goToMainActivity();
    }

    @Override
    public void onUsernameChanged(String username) {
        getMvpView().changePasswordLayoutVisibility(false);
        if(username.trim().length()>3){
            getMvpView().hideJoin();
        } else {
            getMvpView().showJoin();
        }
    }

    @Override
    public void forgotPwd(String username, String userLocale) {
        getMvpView().showLoading();
        JoinRequest request = new JoinRequest(username,userLocale);
        getCompositeDisposable().add(getDataManager()
                .forgotPwd(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse response) throws Exception {
                        if (response.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            //getMvpView().updateExpertise(expertiseResponse.getData());
                            getMvpView().showSuccessDialog();
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
