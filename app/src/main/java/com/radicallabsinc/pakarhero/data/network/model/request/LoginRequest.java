package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("platform")
    @Expose
    private String platform;

    @SerializedName("pushToken")
    @Expose
    private String pushToken;

    public LoginRequest(String userName) {
        this.userName = userName;
    }

    public LoginRequest(String userName, String password, String pusthToken) {
        this.userName = userName;
        this.password = password;
        this.platform = "gcm";
        this.pushToken = pusthToken;
    }
}
