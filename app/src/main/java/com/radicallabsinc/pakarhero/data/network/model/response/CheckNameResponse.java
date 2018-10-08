package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckNameResponse extends BaseResponse{

    @SerializedName("data")
    @Expose
    private CheckNameData data;

    public static class CheckNameData {
        @SerializedName("userNameAvailable")
        @Expose
        private boolean userNameAvailable;

        public boolean isUserNameAvailable() {
            return userNameAvailable;
        }
    }

    public CheckNameData getData() {
        return data;
    }
}
