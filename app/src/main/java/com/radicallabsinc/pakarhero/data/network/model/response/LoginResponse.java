package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private LoginData data;

    public static class LoginData {
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("countRatings")
        @Expose
        private double countRatings;
        @SerializedName("sumRatings")
        @Expose
        private double sumRatings;
        @SerializedName("memberStartDate")
        @Expose
        private String memberStartDate;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("userType")
        @Expose
        private String userType;
        @SerializedName("activeInd")
        @Expose
        private Integer activeInd;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("resumes")
        @Expose
        private List<WorkHistoryResponse.WorkHistoryData> resumes;
        @SerializedName("userId")
        @Expose
        private Long userId;
        @SerializedName("userLocale")
        @Expose
        private String userLocale;
        @SerializedName("verifyCode")
        @Expose
        private String verifyCode;
        @SerializedName("onlineInd")
        @Expose
        private Integer onlineInd;
        @SerializedName("languages")
        @Expose
        private String languages;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("userDesc")
        @Expose
        private String userDesc;
        @SerializedName("certs")
        @Expose
        private List<CertificationResponse.CertificationData> certs;
        @SerializedName("authToken")
        @Expose
        private String authToken;
        @SerializedName("userImg")
        @Expose
        private String userImg;
        @SerializedName("skills")
        @Expose
        private List<SkillResponse.SkillData> skills;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("userNameAvailable")
        @Expose
        private boolean userNameAvailable;
        @SerializedName("countryCode")
        @Expose
        private String countryCode;
        @SerializedName("paypalEmail")
        @Expose
        private String paypalEmail;
        @SerializedName("dokuBranch")
        @Expose
        private String dokuBranch;
        @SerializedName("dokuBankActName")
        @Expose
        private String dokuBankActName;
        @SerializedName("dokuBankActNumber")
        @Expose
        private String dokuBankActNumber;
        @SerializedName("dokuBankName")
        @Expose
        private String dokuBankName;

        public String getUserName() {
            return userName;
        }

        public double getCountRatings() {
            return countRatings;
        }

        public double getSumRatings() {
            return sumRatings;
        }

        public String getMemberStartDate() {
            return memberStartDate;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getUserType() {
            return userType;
        }

        public Integer getActiveInd() {
            return activeInd;
        }

        public String getLastName() {
            return lastName;
        }

        public Long getUserId() {
            return userId;
        }

        public String getUserLocale() {
            return userLocale;
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public Integer getOnlineInd() {
            return onlineInd;
        }

        public String getLanguages() {
            return languages;
        }

        public String getPhone() {
            return phone;
        }

        public String getUserDesc() {
            return userDesc;
        }

        public String getAuthToken() {
            return authToken;
        }

        public String getUserImg() {
            return userImg;
        }

        public String getPassword() {
            return password;
        }

        public boolean isUserNameAvailable() {
            return userNameAvailable;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public List<WorkHistoryResponse.WorkHistoryData> getResumes() {
            return resumes;
        }

        public List<CertificationResponse.CertificationData> getCerts() {
            return certs;
        }

        public List<SkillResponse.SkillData> getSkills() {
            return skills;
        }

        public String getPaypalEmail() {
            return paypalEmail;
        }

        public String getDokuBranch() {
            return dokuBranch;
        }

        public String getDokuBankActName() {
            return dokuBankActName;
        }

        public String getDokuBankActNumber() {
            return dokuBankActNumber;
        }

        public String getDokuBankName() {
            return dokuBankName;
        }
    }

    public LoginData getData() {
        return data;
    }
}
