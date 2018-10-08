package com.radicallabsinc.pakarhero.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LovResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<LovData> data;

    public static class LovData {
        @SerializedName("lovCategory")
        @Expose
        private String lovCategory;
        @SerializedName("lovValue")
        @Expose
        private String lovValue;
        @SerializedName("lovDesc")
        @Expose
        private String lovDesc;

        public String getLovCategory() {
            return lovCategory;
        }

        public String getLovValue() {
            return lovValue;
        }

        public String getLovDesc() {
            return lovDesc;
        }
    }

    public List<LovData> getData() {
        return data;
    }
}
