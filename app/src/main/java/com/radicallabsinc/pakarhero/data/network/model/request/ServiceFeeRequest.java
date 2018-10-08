package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceFeeRequest {
    @SerializedName("userId")
    @Expose
    private long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("currency")
    @Expose
    private String currency;

    public ServiceFeeRequest(long userId, String authToken, String currency) {
        this.userId = userId;
        this.authToken = authToken;
        this.currency = currency;
    }
}
