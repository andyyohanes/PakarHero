package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CertificationRequest {
    @SerializedName("userId")
    @Expose
    private Long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("expertId")
    @Expose
    private Long expertId;
    @SerializedName("certification")
    @Expose
    private String certification;
    @SerializedName("period")
    @Expose
    private String period;
    @SerializedName("certId")
    @Expose
    private Integer certId;

    public CertificationRequest(Long userId, String authToken, String period, String certification) {
        this.userId = userId;
        this.authToken = authToken;
        this.certification = certification;
        this.period = period;
    }

    public CertificationRequest(Long userId, String authToken, Long expertId) {
        this.userId = userId;
        this.authToken = authToken;
        this.expertId = expertId;
    }

    public CertificationRequest(Integer certId, Long userId, String authToken) {
        this.certId = certId;
        this.userId = userId;
        this.authToken = authToken;
    }
}
