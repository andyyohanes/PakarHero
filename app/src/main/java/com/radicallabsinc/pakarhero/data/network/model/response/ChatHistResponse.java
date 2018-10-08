package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatHistResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<ChatData> data;

    public static class ChatData{
        @SerializedName("contentType")
        @Expose
        private String contentType;
        @SerializedName("epochEnd")
        @Expose
        private long epochEnd;
        @SerializedName("receiverFirstName")
        @Expose
        private String receiverFirstName;
        @SerializedName("msgId")
        @Expose
        private long msgId;
        @SerializedName("senderId")
        @Expose
        private long senderId;
        @SerializedName("epochStart")
        @Expose
        private long epochStart;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("receiverId")
        @Expose
        private long receiverId;
        @SerializedName("senderFirstName")
        @Expose
        private String senderFirstName;
        @SerializedName("roomId")
        @Expose
        private Integer roomId;

        public String getContentType() {
            return contentType;
        }

        public long getEpochEnd() {
            return epochEnd;
        }

        public String getReceiverFirstName() {
            return receiverFirstName;
        }

        public long getMsgId() {
            return msgId;
        }

        public long getSenderId() {
            return senderId;
        }

        public long getEpochStart() {
            return epochStart;
        }

        public String getContent() {
            return content;
        }

        public long getReceiverId() {
            return receiverId;
        }

        public String getSenderFirstName() {
            return senderFirstName;
        }

        public Integer getRoomId() {
            return roomId;
        }
    }

    public List<ChatData> getData() {
        return data;
    }
}
