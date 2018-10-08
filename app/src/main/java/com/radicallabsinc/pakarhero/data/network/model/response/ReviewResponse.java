package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReviewResponse extends BaseResponse{

    @SerializedName("data")
    @Expose
    private List<ReviewData> data;

    public static class ReviewData implements Serializable{
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("expertFirstName")
        @Expose
        private String expertFirstName;
        @SerializedName("customerFirstName")
        @Expose
        private String customerFirstName;
        @SerializedName("closeReasonCode")
        @Expose
        private String closeReasonCode;
        @SerializedName("customerLastName")
        @Expose
        private String customerLastName;
        @SerializedName("reviewDate")
        @Expose
        private String reviewDate;
        @SerializedName("expertImg")
        @Expose
        private String expertImg;
        @SerializedName("expertLastName")
        @Expose
        private String expertLastName;
        @SerializedName("caseReviewId")
        @Expose
        private Integer caseReviewId;
        @SerializedName("customerImg")
        @Expose
        private String customerImg;
        @SerializedName("expertId")
        @Expose
        private Integer expertId;
        @SerializedName("customerId")
        @Expose
        private Integer customerId;

        public String getComment() {
            return comment;
        }

        public Integer getRating() {
            return rating;
        }

        public String getExpertFirstName() {
            return expertFirstName;
        }

        public String getCustomerFirstName() {
            return customerFirstName;
        }

        public String getCloseReasonCode() {
            return closeReasonCode;
        }

        public String getCustomerLastName() {
            return customerLastName;
        }

        public String getReviewDate() {
            return reviewDate;
        }

        public String getExpertImg() {
            return expertImg;
        }

        public String getExpertLastName() {
            return expertLastName;
        }

        public Integer getCaseReviewId() {
            return caseReviewId;
        }

        public String getCustomerImg() {
            return customerImg;
        }

        public Integer getExpertId() {
            return expertId;
        }

        public Integer getCustomerId() {
            return customerId;
        }
    }

    public List<ReviewData> getData() {
        return data;
    }
}
