package com.radicallabsinc.pakarhero.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpertiseRequest {
    @SerializedName("lovCategory")
    @Expose
    private String lovCategory;
    @SerializedName("userLocale")
    @Expose
    private String userLocale;

    public ExpertiseRequest(String lovCategory, String userLocale) {
        this.lovCategory = lovCategory;
        this.userLocale = userLocale;
    }

    public ExpertiseRequest(String userLocale) {
        this.userLocale = userLocale;
    }
}
