package com.radicallabsinc.pakarhero.di.module;

import android.app.Application;
import android.content.Context;

import com.radicallabsinc.pakarhero.BuildConfig;
import com.radicallabsinc.pakarhero.data.AppDataManager;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.data.db.AppDbHelper;
import com.radicallabsinc.pakarhero.data.db.DbHelper;
import com.radicallabsinc.pakarhero.data.network.ApiHelper;
import com.radicallabsinc.pakarhero.data.network.AppApiHelper;
import com.radicallabsinc.pakarhero.data.prefs.AppPreferencesHelper;
import com.radicallabsinc.pakarhero.data.prefs.PreferencesHelper;
import com.radicallabsinc.pakarhero.di.ApiInfo;
import com.radicallabsinc.pakarhero.di.ApplicationContext;
import com.radicallabsinc.pakarhero.di.DatabaseInfo;
import com.radicallabsinc.pakarhero.di.PreferenceInfo;
import com.radicallabsinc.pakarhero.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    /*@Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }*/

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    /*@Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }*/
}
