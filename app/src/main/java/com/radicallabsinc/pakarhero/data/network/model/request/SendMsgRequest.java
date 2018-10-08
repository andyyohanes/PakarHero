package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMsgRequest {
    @SerializedName("userId")
    @Expose
    private long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("senderId")
    @Expose
    private long senderId;
    @SerializedName("roomId")
    @Expose
    private long roomId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("fileType")
    @Expose
    private String fileType;
    @SerializedName("epochStart")
    @Expose
    private long epochStart;
    @SerializedName("epochEnd")
    @Expose
    private long epochEnd;

    public SendMsgRequest(long userId, String authToken, long senderId, long roomId, String content, long epochStart, long epochEnd) {
        this.userId = userId;
        this.authToken = authToken;
        this.senderId = senderId;
        this.roomId = roomId;
        this.content = content;
        this.epochStart = epochStart;
        this.epochEnd = epochEnd;
    }

    public SendMsgRequest(long userId, String authToken, long senderId, long roomId, String content, String fileType, long epochStart, long epochEnd) {
        this.userId = userId;
        this.authToken = authToken;
        this.senderId = senderId;
        this.roomId = roomId;
        this.content = content;
        this.fileType = fileType;
        this.epochStart = epochStart;
        this.epochEnd = epochEnd;
    }
}
