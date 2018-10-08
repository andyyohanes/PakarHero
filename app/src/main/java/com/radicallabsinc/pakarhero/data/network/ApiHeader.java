package com.radicallabsinc.pakarhero.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.radicallabsinc.pakarhero.di.ApiInfo;

import javax.inject.Inject;

public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;
    private PublicApiHeader mPublicApiHeader;

    /*@Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }*/

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class PublicApiHeader {

        @Inject
        public PublicApiHeader() {
        }
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("user_id")
        private Long mUserId;

        @Expose
        @SerializedName("access_token")
        private String mAccessToken;

        public ProtectedApiHeader(Long mUserId, String mAccessToken) {
            this.mUserId = mUserId;
            this.mAccessToken = mAccessToken;
        }

        public Long getUserId() {
            return mUserId;
        }

        public void setUserId(Long mUserId) {
            this.mUserId = mUserId;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            mAccessToken = accessToken;
        }
    }

}
