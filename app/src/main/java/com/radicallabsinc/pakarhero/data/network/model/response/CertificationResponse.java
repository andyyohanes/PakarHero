package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CertificationResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<CertificationData> data;

    public static class CertificationData implements Serializable{
        @SerializedName("certId")
        @Expose
        private Integer certId;
        @SerializedName("certification")
        @Expose
        private String certification;
        @SerializedName("period")
        @Expose
        private String period;
        @SerializedName("expertId")
        @Expose
        private String expertId;

        public Integer getCertId() {
            return certId;
        }

        public String getCertification() {
            return certification;
        }

        public String getPeriod() {
            return period;
        }

        public String getExpertId() {
            return expertId;
        }
    }

    public List<CertificationData> getData() {
        return data;
    }
}
