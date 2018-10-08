package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillRequest {
    @SerializedName("userId")
    @Expose
    private Long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("expertId")
    @Expose
    private Long expertId;
    @SerializedName("skillId")
    @Expose
    private Integer skillId;
    @SerializedName("userLocale")
    @Expose
    private String userLocale;
    @SerializedName("expertiseCode")
    @Expose
    private String expertiseCode;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("pricePerSession")
    @Expose
    private Integer pricePerSession;
    @SerializedName("sessionLength")
    @Expose
    private Integer sessionLength;
    @SerializedName("sessionUnit")
    @Expose
    private String sessionUnit;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("imgType")
    @Expose
    private String imgType;

    public SkillRequest(Long userId, String authToken, Long expertId, String userLocale) {
        this.userId = userId;
        this.authToken = authToken;
        this.expertId = expertId;
        this.userLocale = userLocale;
    }

    public SkillRequest(Long userId, String authToken, Integer skillId, String userLocale) {
        this.userId = userId;
        this.authToken = authToken;
        this.skillId = skillId;
        this.userLocale = userLocale;
    }

    public SkillRequest(Long userId, String authToken, String userLocale, String expertiseCode, String currency, Integer pricePerSession, Integer sessionLength, String sessionUnit, String img, String imgType) {
        this.userId = userId;
        this.authToken = authToken;
        this.userLocale = userLocale;
        this.expertiseCode = expertiseCode;
        this.currency = currency;
        this.pricePerSession = pricePerSession;
        this.sessionLength = sessionLength;
        this.sessionUnit = sessionUnit;
        this.img = img;
        this.imgType = imgType;
    }
}
