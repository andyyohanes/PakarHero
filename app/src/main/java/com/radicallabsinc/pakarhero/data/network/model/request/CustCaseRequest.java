package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustCaseRequest {
    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("authToken")
    @Expose
    private String authToken;

    public CustCaseRequest(String customerId, String authToken) {
        this.customerId = customerId;
        this.authToken = authToken;
    }
}
