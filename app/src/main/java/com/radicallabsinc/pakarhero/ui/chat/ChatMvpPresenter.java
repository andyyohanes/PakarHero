package com.radicallabsinc.pakarhero.ui.chat;

import com.radicallabsinc.pakarhero.ui.base.MvpPresenter;

public interface ChatMvpPresenter<V extends ChatMvpView> extends MvpPresenter<V> {
    void onViewPrepared(long roomId);

    void sendMessage(long roomId, String content, long epochStart, long epochEnd);

    void getMessages(long roomId, Integer msgId);

    void uploadImage(long roomId, String content, String fileType, long epochStart, long epochEnd);
}
