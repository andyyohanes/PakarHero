package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewRequest {
    @SerializedName("expertId")
    @Expose
    private Long expertId;

    public ReviewRequest(Long expertId) {
        this.expertId = expertId;
    }
}
