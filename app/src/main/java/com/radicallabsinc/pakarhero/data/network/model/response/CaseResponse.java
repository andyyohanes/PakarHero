package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CaseResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<CaseData> data;

    public static class CaseData implements Serializable{
        @SerializedName("customerCountRatings")
        @Expose
        private Integer customerCountRating;
        @SerializedName("customerName")
        @Expose
        private String customerName;
        @SerializedName("caseStatus")
        @Expose
        private String caseStatus;
        @SerializedName("totalUsedSessions")
        @Expose
        private Integer totalUsedSessions;
        @SerializedName("caseId")
        @Expose
        private String caseId;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("caseCloseDate")
        @Expose
        private String caseCloseDate;
        @SerializedName("customerImg")
        @Expose
        private String customerImg;
        @SerializedName("expSessionUnitDesc")
        @Expose
        private String expSessionUnitDesc;
        @SerializedName("casePhotos")
        @Expose
        private List<CasePhotoDataResponse> casePhotos;
        @SerializedName("totalAvailableSessions")
        @Expose
        private Integer totalAvailableSessions;
        @SerializedName("caseRefId")
        @Expose
        private String caseRefId;
        @SerializedName("expertFirstName")
        @Expose
        private String expertFirstName;
        @SerializedName("expertSumRatings")
        @Expose
        private Integer expertSumRatings;
        @SerializedName("customerFirstName")
        @Expose
        private String customerFirstName;
        @SerializedName("totalPaid")
        @Expose
        private Integer totalPaid;
        @SerializedName("review")
        @Expose
        private ReviewResponse.ReviewData review;
        @SerializedName("expertName")
        @Expose
        private String expertName;
        @SerializedName("custSessionUnitDesc")
        @Expose
        private String custSessionUnitDesc;
        @SerializedName("expertLastName")
        @Expose
        private String expertLastName;
        @SerializedName("details")
        @Expose
        private List<CaseDetailDataResponse> details;
        @SerializedName("sessionUnit")
        @Expose
        private String sessionUnit;
        @SerializedName("expertLocale")
        @Expose
        private String expertLocale;
        @SerializedName("expertStartDate")
        @Expose
        private String expertStartDate;
        @SerializedName("customerLastName")
        @Expose
        private String customerLastName;
        @SerializedName("chatRoomId")
        @Expose
        private String chatRoomId;
        @SerializedName("expertId")
        @Expose
        private String expertId;
        @SerializedName("customerSumRatings")
        @Expose
        private Integer customerSumRatings;
        @SerializedName("sessionLength")
        @Expose
        private Double sessionLength;
        @SerializedName("caseStartDate")
        @Expose
        private String caseStartDate;
        @SerializedName("customerLocale")
        @Expose
        private String customerLocale;
        @SerializedName("custSessionUsedUnitDesc")
        @Expose
        private String custSessionUsedUnitDesc;
        @SerializedName("pricePerSession")
        @Expose
        private Integer pricePerSession;
        @SerializedName("expertImg")
        @Expose
        private String expertImg;
        @SerializedName("customerStartDate")
        @Expose
        private String customerStartDate;
        @SerializedName("expSessionUsedUnitDesc")
        @Expose
        private String expSessionUsedUnitDesc;
        @SerializedName("expertiseCode")
        @Expose
        private String expertiseCode;
        @SerializedName("customerId")
        @Expose
        private String customerId;
        @SerializedName("sessionUsedUnit")
        @Expose
        private String sessionUsedUnit;
        @SerializedName("expertCountRatings")
        @Expose
        private Integer expertCountRatings;

        public Integer getCustomerCountRating() {
            return customerCountRating;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getCaseStatus() {
            return caseStatus;
        }

        public Integer getTotalUsedSessions() {
            return totalUsedSessions;
        }

        public String getCaseId() {
            return caseId;
        }

        public String getCurrency() {
            return currency;
        }

        public String getCaseCloseDate() {
            return caseCloseDate;
        }

        public String getCustomerImg() {
            return customerImg;
        }

        public String getExpSessionUnitDesc() {
            return expSessionUnitDesc;
        }

        public List<CasePhotoDataResponse> getCasePhotos() {
            return casePhotos;
        }

        public Integer getTotalAvailableSessions() {
            return totalAvailableSessions;
        }

        public String getCaseRefId() {
            return caseRefId;
        }

        public String getExpertFirstName() {
            return expertFirstName;
        }

        public Integer getExpertSumRatings() {
            return expertSumRatings;
        }

        public String getCustomerFirstName() {
            return customerFirstName;
        }

        public Integer getTotalPaid() {
            return totalPaid;
        }

        public ReviewResponse.ReviewData getReview() {
            return review;
        }

        public String getExpertName() {
            return expertName;
        }

        public String getCustSessionUnitDesc() {
            return custSessionUnitDesc;
        }

        public String getExpertLastName() {
            return expertLastName;
        }

        public List<CaseDetailDataResponse> getDetails() {
            return details;
        }

        public String getSessionUnit() {
            return sessionUnit;
        }

        public String getExpertLocale() {
            return expertLocale;
        }

        public String getExpertStartDate() {
            return expertStartDate;
        }

        public String getCustomerLastName() {
            return customerLastName;
        }

        public String getChatRoomId() {
            return chatRoomId;
        }

        public String getExpertId() {
            return expertId;
        }

        public Integer getCustomerSumRatings() {
            return customerSumRatings;
        }

        public Double getSessionLength() {
            return sessionLength;
        }

        public String getCaseStartDate() {
            return caseStartDate;
        }

        public String getCustomerLocale() {
            return customerLocale;
        }

        public String getCustSessionUsedUnitDesc() {
            return custSessionUsedUnitDesc;
        }

        public Integer getPricePerSession() {
            return pricePerSession;
        }

        public String getExpertImg() {
            return expertImg;
        }

        public String getCustomerStartDate() {
            return customerStartDate;
        }

        public String getExpSessionUsedUnitDesc() {
            return expSessionUsedUnitDesc;
        }

        public String getExpertiseCode() {
            return expertiseCode;
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getSessionUsedUnit() {
            return sessionUsedUnit;
        }

        public Integer getExpertCountRatings() {
            return expertCountRatings;
        }
    }

    public List<CaseData> getData() {
        return data;
    }
}
