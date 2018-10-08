package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveOrderRequest {
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("caseId")
    @Expose
    private String caseId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("serviceFee")
    @Expose
    private double serviceFee;
    @SerializedName("payMethod")
    @Expose
    private String payMethod;

    public SaveOrderRequest(long customerId, String authToken, String caseId, String orderId, String currency, double amount, double serviceFee, String payMethod) {
        this.customerId = customerId;
        this.authToken = authToken;
        this.caseId = caseId;
        this.orderId = orderId;
        this.currency = currency;
        this.amount = amount;
        this.serviceFee = serviceFee;
        this.payMethod = payMethod;
    }
}
