package com.radicallabsinc.pakarhero.ui.chat;

import com.androidnetworking.error.ANError;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.network.model.request.ChatHistRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.GetMessageRequest;
import com.radicallabsinc.pakarhero.data.network.model.request.SendMsgRequest;
import com.radicallabsinc.pakarhero.data.network.model.response.ChatHistResponse;
import com.radicallabsinc.pakarhero.data.network.model.response.SendMsgResponse;
import com.radicallabsinc.pakarhero.ui.base.BasePresenter;
import com.radicallabsinc.pakarhero.utils.AppConstants;
import com.radicallabsinc.pakarhero.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ChatPresenter<V extends ChatMvpView> extends BasePresenter<V> implements ChatMvpPresenter<V> {

    @Inject
    public ChatPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(long roomId) {
        getMvpView().showLoading();
        ChatHistRequest chatHistRequest = new ChatHistRequest(getDataManager().getCurrentUserId(),getDataManager().getAccessToken(),roomId);
        getCompositeDisposable().add(getDataManager()
                .getChatHistory(chatHistRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ChatHistResponse>() {
                    @Override
                    public void accept(ChatHistResponse chatHistResponse) throws Exception {
                        if (chatHistResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().updateChat(chatHistResponse.getData(),getDataManager().getCurrentUserId());
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
    public void sendMessage(final long roomId, String content, long epochStart, long epochEnd) {
        SendMsgRequest sendMsgRequest = new SendMsgRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), getDataManager().getCurrentUserId(), roomId, content, epochStart, epochEnd);
        getCompositeDisposable().add(getDataManager()
                .sendMessage(sendMsgRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SendMsgResponse>() {
                    @Override
                    public void accept(SendMsgResponse sendMsgResponse) throws Exception {
                        if (sendMsgResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            //getMvpView().updateChat(chatHistResponse.getData(),getDataManager().getCurrentUserId());
                            getMessages(roomId,sendMsgResponse.getData().getMsgId()-1);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void getMessages(long roomId,Integer msgId) {
        GetMessageRequest getMessageRequest = new GetMessageRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), roomId, msgId, "NEXT");
        getCompositeDisposable().add(getDataManager()
                .getMessages(getMessageRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ChatHistResponse>() {
                    @Override
                    public void accept(ChatHistResponse chatHistResponse) throws Exception {
                        if (chatHistResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            getMvpView().updateChat(chatHistResponse.getData().get(0));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void uploadImage(final long roomId, String content, String fileType, long epochStart, long epochEnd) {
        getMvpView().showLoading();
        SendMsgRequest sendMsgRequest = new SendMsgRequest(getDataManager().getCurrentUserId(), getDataManager().getAccessToken(), getDataManager().getCurrentUserId(), roomId, content, fileType, epochStart, epochEnd);
        getCompositeDisposable().add(getDataManager()
                .uploadImage(sendMsgRequest)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<SendMsgResponse>() {
                    @Override
                    public void accept(SendMsgResponse sendMsgResponse) throws Exception {
                        if (sendMsgResponse.getStatus().equalsIgnoreCase(AppConstants.STATUS_CODE_SUCCESS)) {
                            //getMvpView().updateChat(chatHistResponse.getData(),getDataManager().getCurrentUserId());
                            getMessages(roomId,sendMsgResponse.getData().getMsgId()-1);
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
