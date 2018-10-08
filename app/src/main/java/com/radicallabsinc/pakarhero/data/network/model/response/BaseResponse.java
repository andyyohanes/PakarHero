package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;

    public String getStatus() {
        return status;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
