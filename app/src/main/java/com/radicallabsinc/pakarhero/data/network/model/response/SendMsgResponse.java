package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMsgResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private SendMsgData data;

    public static class SendMsgData{
        @SerializedName("msgId")
        @Expose
        private Integer msgId;

        public Integer getMsgId() {
            return msgId;
        }
    }

    public SendMsgData getData() {
        return data;
    }
}
