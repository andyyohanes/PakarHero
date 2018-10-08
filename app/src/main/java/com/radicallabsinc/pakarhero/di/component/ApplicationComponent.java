package com.radicallabsinc.pakarhero.di.component;

import android.app.Application;
import android.content.Context;

import com.radicallabsinc.pakarhero.PakarHeroApp;
import com.radicallabsinc.pakarhero.data.DataManager;
import com.radicallabsinc.pakarhero.di.ApplicationContext;
import com.radicallabsinc.pakarhero.di.module.ApplicationModule;
import com.radicallabsinc.pakarhero.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(PakarHeroApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
