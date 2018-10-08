package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutRequest {

    @SerializedName("userId")
    @Expose
    private Long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;

    public LogoutRequest(Long userId, String authToken) {
        this.userId = userId;
        this.authToken = authToken;
    }
}
