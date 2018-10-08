package com.radicallabsinc.pakarhero.data;

import com.radicallabsinc.pakarhero.data.network.ApiHelper;
import com.radicallabsinc.pakarhero.data.prefs.PreferencesHelper;

public interface DataManager extends PreferencesHelper, ApiHelper{

    void setUserAsLoggedOut();

    void updateUserInfo(
            String accessToken,
            Long userId,
            String password,
            String gcmToken,
            LoggedInMode loggedInMode,
            String userName,
            String userType,
            String userLocale,
            String profilePicPath);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
