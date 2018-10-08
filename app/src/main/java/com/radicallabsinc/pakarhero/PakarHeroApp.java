package com.radicallabsinc.pakarhero;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.di.component.ApplicationComponent;
import com.radicallabsinc.pakarhero.di.component.DaggerApplicationComponent;
import com.radicallabsinc.pakarhero.di.module.ApplicationModule;
import com.radicallabsinc.pakarhero.utils.AppLogger;

import javax.inject.Inject;

public class PakarHeroApp extends MultiDexApplication {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        //CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
