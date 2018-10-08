package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    List<NotificationData> data;

    public static class NotificationData {
        @SerializedName("caseRefId")
        @Expose
        private String caseRefId;
        @SerializedName("notificationDate")
        @Expose
        private String notificationDate;
        @SerializedName("caseId")
        @Expose
        private String caseId;
        @SerializedName("expertId")
        @Expose
        private long expertId;
        @SerializedName("notificationId")
        @Expose
        private long notificationId;
        @SerializedName("roomId")
        @Expose
        private long roomId;
        @SerializedName("customerId")
        @Expose
        private long customerId;

        public String getCaseRefId() {
            return caseRefId;
        }

        public String getNotificationDate() {
            return notificationDate;
        }

        public String getCaseId() {
            return caseId;
        }

        public long getExpertId() {
            return expertId;
        }

        public long getNotificationId() {
            return notificationId;
        }

        public long getRoomId() {
            return roomId;
        }

        public long getCustomerId() {
            return customerId;
        }
    }

    public List<NotificationData> getData() {
        return data;
    }
}
