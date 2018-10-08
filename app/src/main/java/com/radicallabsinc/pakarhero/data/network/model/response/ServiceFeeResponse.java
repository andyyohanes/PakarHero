package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceFeeResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private ServiceFeeData data;

    public static class ServiceFeeData{
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("serviceFee")
        @Expose
        private double serviceFee;

        public String getCurrency() {
            return currency;
        }

        public double getServiceFee() {
            return serviceFee;
        }
    }

    public ServiceFeeData getData() {
        return data;
    }
}
