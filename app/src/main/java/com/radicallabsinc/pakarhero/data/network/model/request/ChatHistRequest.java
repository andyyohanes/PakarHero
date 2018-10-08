package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatHistRequest {
    @SerializedName("userId")
    @Expose
    private long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("roomId")
    @Expose
    private long roomId;

    public ChatHistRequest(Long userId, String authToken, long roomId) {
        this.userId = userId;
        this.authToken = authToken;
        this.roomId = roomId;
    }
}
