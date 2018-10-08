package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationRequest {
    @SerializedName("userId")
    @Expose
    private long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("roomId")
    @Expose
    private long roomId;

    public NotificationRequest(long userId, String authToken) {
        this.userId = userId;
        this.authToken = authToken;
    }

    public NotificationRequest(long userId, String authToken, long roomId) {
        this.userId = userId;
        this.authToken = authToken;
        this.roomId = roomId;
    }
}
