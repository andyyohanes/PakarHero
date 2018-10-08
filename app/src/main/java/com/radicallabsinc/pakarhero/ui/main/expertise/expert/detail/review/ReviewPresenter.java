package com.radicallabsinc.pakarhero.ui.main.expertise.expert.detail.review;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.ReviewRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.ReviewResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ReviewPresenter<V extends ReviewMvpView> extends BasePresenter<V> implements ReviewMvpPresenter<V> {

    @Inject
    public ReviewPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(Long expertId) {
        getMvpView().showLoading();
        ReviewRequest reviewRequest = new ReviewRequest(expertId);
        getCompositeDisposable().add(getDataManager()
                .getReviews(reviewRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ReviewResponse>() {
                    @Override
                    public void accept(ReviewResponse reviewResponse) throws Exception {
                        if (reviewResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().updateReview(reviewResponse.getData());
                        } else {
                            //getMvpView().showNotFound();
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
