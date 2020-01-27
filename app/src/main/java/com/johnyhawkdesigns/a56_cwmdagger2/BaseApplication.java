package com.johnyhawkdesigns.a56_cwmdagger2;

import com.johnyhawkdesigns.a56_cwmdagger2.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

// NOTE: Don't forget to add in AndroidManifest.xml android:name=".BaseApplication"
public class BaseApplication extends DaggerApplication {


    // In general
    // Components = *Services*
    // Activities/Fragments = Clients
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
