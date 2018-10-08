package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayPalRequest {
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("tx")
    @Expose
    private String tx;

    public PayPalRequest(long customerId, String authToken, String orderId, String tx) {
        this.customerId = customerId;
        this.authToken = authToken;
        this.orderId = orderId;
        this.tx = tx;
    }
}
