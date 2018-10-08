package com.radicallabsinc.pakarhero.ui.main.dashboard.customer_case;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.ExpertCaseRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.ExpertiseRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.CaseResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.ExpertiseResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class CustomerCasePresenter<V extends CustomerCaseMvpView> extends BasePresenter<V> implements CustomerCaseMvpPresenter<V> {
    @Inject
    public CustomerCasePresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        ExpertCaseRequest expertCaseRequest = new ExpertCaseRequest(String.valueOf(getDataManager().getCurrentUserId()), getDataManager().getAccessToken());
        getCompositeDisposable().add(getDataManager()
                .getExpertCase(expertCaseRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CaseResponse>() {
                    @Override
                    public void accept(CaseResponse caseResponse) throws Exception {
                        if (caseResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            List<CaseResponse.CaseData> activeCaseList = new ArrayList<>();
                            List<CaseResponse.CaseData> inactiveCaseList = new ArrayList<>();
                            for(int i=0;i<caseResponse.getData().size();i++) {
                                if(caseResponse.getData().get(i).getCaseStatus().equalsIgnoreCase("close")||caseResponse.getData().get(i).getCaseStatus().equalsIgnoreCase("rejected")){
                                    inactiveCaseList.add(caseResponse.getData().get(i));
                                } else {
                                    activeCaseList.add(caseResponse.getData().get(i));
                                }
                            }
                            getMvpView().updateCase(activeCaseList,inactiveCaseList);
                            //getMvpView().updateExpertise(expertiseResponse.getData());
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
