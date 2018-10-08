package com.radicallabsinc.pakarhero.data.prefs;

import com.radicallabsinc.pakarhero.data.DataManager;

public interface PreferencesHelper {
    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentUserPassword();

    void setCurrentUserPassword(String password);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserType();

    void setCurrentUserType(String userType);

    String getCurrentUserLocale();

    void setCurrentUserLocale(String userLocale);

    String getCurrentGCMToken();

    void setCurrentGCMToken(String gcmToken);
}
