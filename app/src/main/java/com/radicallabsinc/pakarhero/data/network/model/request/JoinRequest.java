package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoinRequest {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("userLocale")
    @Expose
    private String userLocale;

    public JoinRequest(String userName, String password, String countryCode, String phone, String userLocale) {
        this.userName = userName;
        this.password = password;
        this.countryCode = countryCode;
        this.phone = phone;
        this.userLocale = userLocale;
    }

    public JoinRequest(String userName, String userLocale) {
        this.userName = userName;
        this.userLocale = userLocale;
    }
}
