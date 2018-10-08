package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMessageRequest {
    @SerializedName("userId")
    @Expose
    private long userId;
    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("roomId")
    @Expose
    private long roomId;
    @SerializedName("msgId")
    @Expose
    private Integer msgId;
    @SerializedName("pageFlag")
    @Expose
    private String pageFlag;

    public GetMessageRequest(long userId, String authToken, long roomId, Integer msgId, String pageFlag) {
        this.userId = userId;
        this.authToken = authToken;
        this.roomId = roomId;
        this.msgId = msgId;
        this.pageFlag = pageFlag;
    }
}
