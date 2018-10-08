package com.radicallabsinc.pakarhero.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.di.ApplicationContext;
import com.radicallabsinc.pakarhero.di.PreferenceInfo;
import com.radicallabsinc.pakarhero.utils.AppConstants;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_PASSWORD = "PREF_KEY_CURRENT_USER_PASSWORD";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_TYPE = "PREF_KEY_CURRENT_USER_TYPE";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL
            = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_CURRENT_USER_LOCALE = "PREF_KEY_CURRENT_USER_LOCALE";
    private static final String PREF_KEY_CURRENT_GCM_TOKEN = "PREF_KEY_CURRENT_GCM_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public String getCurrentUserPassword() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PASSWORD,null);
    }

    @Override
    public void setCurrentUserPassword(String password) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PASSWORD, password).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getCurrentUserType() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_TYPE, null);
    }

    @Override
    public void setCurrentUserType(String userType) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_TYPE, userType).apply();
    }

    @Override
    public String getCurrentUserLocale() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_LOCALE, null);
    }

    @Override
    public void setCurrentUserLocale(String userLocale) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_LOCALE, userLocale).apply();
    }

    @Override
    public String getCurrentGCMToken() {
        return mPrefs.getString(PREF_KEY_CURRENT_GCM_TOKEN, null);
    }

    @Override
    public void setCurrentGCMToken(String gcmToken) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_GCM_TOKEN, gcmToken).apply();
    }
}
