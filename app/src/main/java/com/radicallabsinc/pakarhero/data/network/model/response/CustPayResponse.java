package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustPayResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private CustPayData data;

    public static class CustPayData{
        @SerializedName("orderId")
        @Expose
        private String orderId;
        @SerializedName("caseRefId")
        @Expose
        private String caseRefId;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("payMethod")
        @Expose
        private String payMethod;
        @SerializedName("caseId")
        @Expose
        private String caseId;
        @SerializedName("serviceFee")
        @Expose
        private Double serviceFee;
        @SerializedName("amount")
        @Expose
        private Double amount;
        @SerializedName("paymentDate")
        @Expose
        private String paymentDate;
        @SerializedName("paymentId")
        @Expose
        private Integer paymentId;
        @SerializedName("payStatus")
        @Expose
        private String payStatus;

        public String getOrderId() {
            return orderId;
        }

        public String getCaseRefId() {
            return caseRefId;
        }

        public String getCurrency() {
            return currency;
        }

        public String getPayMethod() {
            return payMethod;
        }

        public String getCaseId() {
            return caseId;
        }

        public Double getServiceFee() {
            return serviceFee;
        }

        public Double getAmount() {
            return amount;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public Integer getPaymentId() {
            return paymentId;
        }

        public String getPayStatus() {
            return payStatus;
        }
    }

    public CustPayData getData() {
        return data;
    }
}
