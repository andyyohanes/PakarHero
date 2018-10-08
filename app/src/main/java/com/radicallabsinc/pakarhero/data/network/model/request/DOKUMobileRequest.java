package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DOKUMobileRequest {
    @SerializedName("dokuTokenStr")
    @Expose
    private String dokuTokenStr;
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("dokuChannel")
    @Expose
    private String dokuChannel;

    public DOKUMobileRequest(String dokuTokenStr, long customerId, String authToken, String orderId, String dokuChannel) {
        this.dokuTokenStr = dokuTokenStr;
        this.customerId = customerId;
        this.authToken = authToken;
        this.orderId = orderId;
        this.dokuChannel = dokuChannel;
    }
}
