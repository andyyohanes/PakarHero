package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpertRequest {
    @SerializedName("expertiseCode")
    @Expose
    private String expertiseCode;

    public ExpertRequest(String expertiseCode) {
        this.expertiseCode = expertiseCode;
    }
}
