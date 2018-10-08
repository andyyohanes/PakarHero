package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExpertResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<ExpertData> data;

    public static class ExpertData implements Serializable{
        @SerializedName("expertFirstName")
        @Expose
        private String expertFirstName;
        @SerializedName("expertSumRatings")
        @Expose
        private Integer expertSumRatings;
        @SerializedName("expertLocale")
        @Expose
        private String expertLocale;
        @SerializedName("expertMemberStartDate")
        @Expose
        private String expertMemberStartDate;
        @SerializedName("expertUserName")
        @Expose
        private String expertUserName;
        @SerializedName("expertOnlineInd")
        @Expose
        private Integer expertOnlineInd;
        @SerializedName("expertImg")
        @Expose
        private String expertImg;
        @SerializedName("expertSkills")
        @Expose
        private List<SkillResponse.SkillData> expertSkills;
        @SerializedName("expertLastName")
        @Expose
        private String expertLastName;
        @SerializedName("expertDesc")
        @Expose
        private String expertDesc;
        @SerializedName("expertLanguages")
        @Expose
        private String expertLanguages;
        @SerializedName("expertId")
        @Expose
        private Long expertId;
        @SerializedName("expertCerts")
        @Expose
        private List<CertificationResponse.CertificationData> expertCerts;
        @SerializedName("expertCountRatings")
        @Expose
        private Integer expertCountRatings;
        @SerializedName("expertResumes")
        @Expose
        private List<WorkHistoryResponse.WorkHistoryData> expertResumes;
        @SerializedName("expertPhone")
        @Expose
        private String expertPhone;

        public String getExpertFirstName() {
            return expertFirstName;
        }

        public Integer getExpertSumRatings() {
            return expertSumRatings;
        }

        public String getExpertLocale() {
            return expertLocale;
        }

        public String getExpertMemberStartDate() {
            return expertMemberStartDate;
        }

        public String getExpertUserName() {
            return expertUserName;
        }

        public Integer getExpertOnlineInd() {
            return expertOnlineInd;
        }

        public String getExpertImg() {
            return expertImg;
        }

        public List<SkillResponse.SkillData> getExpertSkills() {
            return expertSkills;
        }

        public String getExpertLastName() {
            return expertLastName;
        }

        public String getExpertDesc() {
            return expertDesc;
        }

        public String getExpertLanguages() {
            return expertLanguages;
        }

        public Long getExpertId() {
            return expertId;
        }

        public List<CertificationResponse.CertificationData> getExpertCerts() {
            return expertCerts;
        }

        public Integer getExpertCountRatings() {
            return expertCountRatings;
        }

        public List<WorkHistoryResponse.WorkHistoryData> getExpertResumes() {
            return expertResumes;
        }

        public String getExpertPhone() {
            return expertPhone;
        }
    }

    public List<ExpertData> getData() {
        return data;
    }
}
