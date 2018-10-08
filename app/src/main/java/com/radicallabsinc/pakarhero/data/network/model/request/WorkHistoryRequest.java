package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkHistoryRequest {
    @SerializedName("userId")
    @Expose
    private Long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("expertId")
    @Expose
    private String expertId;
    @SerializedName("resumeId")
    @Expose
    private Integer resumeId;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("period")
    @Expose
    private String period;
    @SerializedName("jobDesc")
    @Expose
    private String jobDesc;

    public WorkHistoryRequest(Long userId, String authToken, String expertId) {
        this.userId = userId;
        this.authToken = authToken;
        this.expertId = expertId;
    }

    public WorkHistoryRequest(Long userId, String authToken, Integer resumeId) {
        this.userId = userId;
        this.authToken = authToken;
        this.resumeId = resumeId;
    }

    public WorkHistoryRequest(Long userId, String authToken, String company, String period, String jobDesc) {
        this.userId = userId;
        this.authToken = authToken;
        this.company = company;
        this.period = period;
        this.jobDesc = jobDesc;
    }
}
