package com.radicallabsinc.pakarhero.ui.main;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.LogoutRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.BaseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.data.network.model.request.ExpertiseRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.LovResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {
    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public boolean checkAuthorization() {
        getExpertiseData();
        getLovData();
        if(getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()){
            getMvpView().unAuthorizedView();
            return false;
        } else {
            getMvpView().authorizedView();
            return true;
        }
    }

    @Override
    public void doLogout() {
        getMvpView().showLoading();
        final LogoutRequest request = new LogoutRequest(getDataManager().getCurrentUserId(),getDataManager().getAccessToken());
        getCompositeDisposable().add(getDataManager()
                .logout(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse response) throws Exception {
                        if(response.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)){
                            getDataManager().setUserAsLoggedOut();
                            getMvpView().refreshLayout();
                        } else if(response.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
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
    public void getExpertiseData() {
        ExpertiseRequest expertiseRequest = new ExpertiseRequest(getDataManager().getCurrentUserLocale());
        getCompositeDisposable().add(getDataManager()
                .getExpertise(expertiseRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExpertiseResponse>() {
                    @Override
                    public void accept(ExpertiseResponse expertiseResponse) throws Exception {
                        if (expertiseResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            for(int i=0;i<expertiseResponse.getData().size();i++)
                                AppConstants.expertiseMap.put(expertiseResponse.getData().get(i).getExpertiseCode(),expertiseResponse.getData().get(i).getExpertiseDesc());
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
    public void getLovData() {
        ExpertiseRequest expertiseRequest = new ExpertiseRequest("SESSIONUNIT",getDataManager().getCurrentUserLocale());
        getCompositeDisposable().add(getDataManager()
                .getLov(expertiseRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LovResponse>() {
                    @Override
                    public void accept(LovResponse lovResponse) throws Exception {
                        if (lovResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            for(int i=0;i<lovResponse.getData().size();i++)
                                AppConstants.lovMap.put(lovResponse.getData().get(i).getLovValue(),lovResponse.getData().get(i).getLovDesc());
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
