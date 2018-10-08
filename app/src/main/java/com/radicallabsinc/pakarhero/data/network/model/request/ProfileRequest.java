package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileRequest {
    @SerializedName("userId")
    @Expose
    private Long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("languages")
    @Expose
    private String languages;
    @SerializedName("paypalEmail")
    @Expose
    private String paypalEmail;
    @SerializedName("dokuBankActName")
    @Expose
    private String dokuBankActName;
    @SerializedName("dokuBankActNumber")
    @Expose
    private String dokuBankActNumber;
    @SerializedName("dokuBankName")
    @Expose
    private String dokuBankName;
    @SerializedName("dokuBranch")
    @Expose
    private String dokuBranch;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("imgType")
    @Expose
    private String imgType;

    public ProfileRequest(Long userId, String authToken, String userName, String password, String firstName, String lastName, String countryCode, String phone, String desc, String languages, String paypalEmail, String dokuBankActName, String dokuBankActNumber, String dokuBankName, String dokuBranch, String img, String imgType) {
        this.userId = userId;
        this.authToken = authToken;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryCode = countryCode;
        this.phone = phone;
        this.desc = desc;
        this.languages = languages;
        this.paypalEmail = paypalEmail;
        this.dokuBankActName = dokuBankActName;
        this.dokuBankActNumber = dokuBankActNumber;
        this.dokuBankName = dokuBankName;
        this.dokuBranch = dokuBranch;
        this.img = img;
        this.imgType = imgType;
    }
}
