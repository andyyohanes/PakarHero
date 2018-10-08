package com.radicallabsinc.pakarhero.di.component;

import com.radicallabsinc.pakarhero.di.PerService;
import com.radicallabsinc.pakarhero.di.module.ServiceModule;
import com.radicallabsinc.pakarhero.service.SyncService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);
}
