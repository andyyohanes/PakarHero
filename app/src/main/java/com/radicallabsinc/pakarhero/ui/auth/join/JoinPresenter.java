package com.radicallabsinc.pakarhero.ui.auth.join;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.JoinRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.BaseResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.CommonUtils;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class JoinPresenter<V extends JoinMvpView> extends BasePresenter<V> implements JoinMvpPresenter<V> {
    @Inject
    public JoinPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onJoinClicked(final String username, String password, final String countryCode, final String phone, String userLocale) {
        JoinRequest request = new JoinRequest(username, CommonUtils.getMD5Hex(password), countryCode, phone, userLocale);
        getCompositeDisposable().add(getDataManager()
                        .join(request)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<BaseResponse>() {
                            @Override
                            public void accept(BaseResponse baseResponse) throws Exception {
                                if(baseResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS))
                                    getMvpView().showSuccessDialog(username, countryCode, phone);
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
