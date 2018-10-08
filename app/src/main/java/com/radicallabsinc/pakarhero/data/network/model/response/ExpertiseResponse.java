package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExpertiseResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<ExpertiseData> data;

    public static class ExpertiseData{
        @SerializedName("expertiseDesc")
        @Expose
        private String expertiseDesc;
        @SerializedName("expertiseLocale")
        @Expose
        private String expertiseLocale;
        @SerializedName("expertiseCode")
        @Expose
        private String expertiseCode;
        @SerializedName("iconColorCode")
        @Expose
        private String iconColorCode;
        @SerializedName("iconName")
        @Expose
        private String iconName;
        @SerializedName("mobileIconName")
        @Expose
        private String mobileIconName;

        public String getExpertiseDesc() {
            return expertiseDesc;
        }

        public String getExpertiseLocale() {
            return expertiseLocale;
        }

        public String getExpertiseCode() {
            return expertiseCode;
        }

        public String getIconColorCode() {
            return iconColorCode;
        }

        public String getIconName() {
            return iconName;
        }

        public String getMobileIconName() {
            return mobileIconName;
        }
    }

    public List<ExpertiseData> getData() {
        return data;
    }
}
