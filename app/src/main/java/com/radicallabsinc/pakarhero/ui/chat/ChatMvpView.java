package com.radicallabsinc.pakarhero.ui.chat;

import com.radicallabsinc.pakarhero.data.network.model.response.ChatHistResponse;
import com.radicallabsinc.pakarhero.ui.base.MvpView;

import java.util.List;

public interface ChatMvpView extends MvpView {

    void updateChat(List<ChatHistResponse.ChatData> chatDataList, long id);

    void updateChat(ChatHistResponse.ChatData chatData);

    void sendMessage(String content, long epochStart, long epochEnd);

    void getMessage();
}
