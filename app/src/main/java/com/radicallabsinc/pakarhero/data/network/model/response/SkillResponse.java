package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SkillResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<SkillData> data;

    public static class SkillData implements Serializable{
        @SerializedName("skillId")
        @Expose
        private Integer skillId;
        @SerializedName("pricePerSession")
        @Expose
        private Integer pricePerSession;
        @SerializedName("activeInd")
        @Expose
        private Integer activeInd;
        @SerializedName("sessionUnitDesc")
        @Expose
        private String sessionUnitDesc;
        @SerializedName("sessionUnit")
        @Expose
        private String sessionUnit;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("expertId")
        @Expose
        private String expertId;
        @SerializedName("expertiseDesc")
        @Expose
        private String expertiseDesc;
        @SerializedName("sessionLength")
        @Expose
        private Integer sessionLength;
        @SerializedName("expertiseCode")
        @Expose
        private String expertiseCode;
        @SerializedName("skillImg")
        @Expose
        private String skillImg;

        public Integer getSkillId() {
            return skillId;
        }

        public Integer getPricePerSession() {
            return pricePerSession;
        }

        public Integer getActiveInd() {
            return activeInd;
        }

        public String getSessionUnitDesc() {
            return sessionUnitDesc;
        }

        public String getSessionUnit() {
            return sessionUnit;
        }

        public String getCurrency() {
            return currency;
        }

        public String getExpertId() {
            return expertId;
        }

        public String getExpertiseDesc() {
            return expertiseDesc;
        }

        public Integer getSessionLength() {
            return sessionLength;
        }

        public String getExpertiseCode() {
            return expertiseCode;
        }

        public String getSkillImg() {
            return skillImg;
        }
    }

    public List<SkillData> getData() {
        return data;
    }
}
