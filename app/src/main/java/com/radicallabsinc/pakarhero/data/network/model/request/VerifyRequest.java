package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyRequest {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("code")
    @Expose
    private String code;

    public VerifyRequest(String userName, String countryCode, String phone, String code) {
        this.userName = userName;
        this.countryCode = countryCode;
        this.phone = phone;
        this.code = code;
    }

    public VerifyRequest(String userName, String phone) {
        this.userName = userName;
        this.phone = phone;
    }
}
