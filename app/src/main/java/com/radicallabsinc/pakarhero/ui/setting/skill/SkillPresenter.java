package com.radicallabsinc.pakarhero.ui.setting.skill;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.ExpertiseRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.SkillRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.LovResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SkillResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SkillPresenter<V extends SkillMvpView> extends BasePresenter<V> implements SkillMvpPresenter<V> {

    @Inject
    public SkillPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        SkillRequest skillRequest = new SkillRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), getDataManager().getCurrentUserId(), getDataManager().getCurrentUserLocale());
        getCompositeDisposable().add(getDataManager()
                .getSkill(skillRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SkillResponse>() {
                    @Override
                    public void accept(SkillResponse skillResponse) throws Exception {
                        if (skillResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().getSkill(skillResponse.getData());
                        } else if(skillResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
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
        ExpertiseRequest expertiseRequest = new ExpertiseRequest(getDataManager().getCurrentUserLocale());
        getCompositeDisposable().add(getDataManager()
                .getExpertise(expertiseRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExpertiseResponse>() {
                    @Override
                    public void accept(ExpertiseResponse expertiseResponse) throws Exception {
                        if (expertiseResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().getExpertiseData(expertiseResponse.getData());
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
        expertiseRequest = new ExpertiseRequest("SESSIONUNIT",getDataManager().getCurrentUserLocale());
        getCompositeDisposable().add(getDataManager()
                .getLov(expertiseRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LovResponse>() {
                    @Override
                    public void accept(LovResponse lovResponse) throws Exception {
                        if (lovResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().getLovData(lovResponse.getData());
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
    public void deleteItem(SkillResponse.SkillData data, final int position) {
        getMvpView().showLoading();
        SkillRequest skillRequest = new SkillRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), data.getSkillId(),getDataManager().getCurrentUserLocale());
        getCompositeDisposable().add(getDataManager()
                .delSkill(skillRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SkillResponse>() {
                    @Override
                    public void accept(SkillResponse skillResponse) throws Exception {
                        if (skillResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().onSuccessDeleteItem(position);
                        } else if(skillResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
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
    public void saveItem(String expertiseCode, String currency, Integer pricePerSession, Integer sessionLength, String sessionUnit, String imageSource, String imageType) {
        getMvpView().showLoading();
        SkillRequest skillRequest = new SkillRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(),getDataManager().getCurrentUserLocale(),expertiseCode,currency,pricePerSession,sessionLength,sessionUnit,imageSource,imageType);
        getCompositeDisposable().add(getDataManager()
                .saveSkill(skillRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SkillResponse>() {
                    @Override
                    public void accept(SkillResponse skillResponse) throws Exception {
                        if (skillResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().onSuccessSaveItem();
                        } else if(skillResponse.getStatus().equalsIgnoreCase(AppConstants.ERROR_INVALID_SESSION)) {
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

    /*@Override
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
                        if (certificationResponse.getStatus().equals("SUCCESS")) {
                            getMvpView().onSuccessSaveItem();
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
    }*/
}
