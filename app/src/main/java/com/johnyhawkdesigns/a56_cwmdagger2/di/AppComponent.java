package com.johnyhawkdesigns.a56_cwmdagger2.di;

import android.app.Application;

import com.johnyhawkdesigns.a56_cwmdagger2.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

// di = dependency injection

// Here AppComponent is a SERVICE and BaseApplication is a CLIENT
@Component(modules = {
                AndroidSupportInjectionModule.class,
        })
public interface AppComponent extends AndroidInjector<BaseApplication> { // inject BaseApplication "into" AppComponent

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application); // This method is used in BaseApplication.class

        AppComponent build(); // This is like a constructor - Also used inside BaseApplication.class
    }

}
