package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustPayRequest {
    @SerializedName("userId")
    @Expose
    private long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("orderId")
    @Expose
    private String orderId;

    public CustPayRequest(long userId, String authToken, String orderId) {
        this.userId = userId;
        this.authToken = authToken;
        this.orderId = orderId;
    }
}
