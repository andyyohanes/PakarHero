package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WorkHistoryResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<WorkHistoryData> data;

    public static class WorkHistoryData implements Serializable{
        @SerializedName("resumeId")
        @Expose
        private Integer resumeId;
        @SerializedName("period")
        @Expose
        private String period;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("jobDesc")
        @Expose
        private String jobDesc;
        @SerializedName("expertId")
        @Expose
        private String expertId;

        public Integer getResumeId() {
            return resumeId;
        }

        public String getPeriod() {
            return period;
        }

        public String getCompany() {
            return company;
        }

        public String getJobDesc() {
            return jobDesc;
        }

        public String getExpertId() {
            return expertId;
        }
    }

    public List<WorkHistoryData> getData() {
        return data;
    }
}
