package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpertCaseRequest {
    @SerializedName("expertId")
    @Expose
    private String expertId;
    @SerializedName("authToken")
    @Expose
    private String authToken;

    public ExpertCaseRequest(String expertId, String authToken) {
        this.expertId = expertId;
        this.authToken = authToken;
    }
}
