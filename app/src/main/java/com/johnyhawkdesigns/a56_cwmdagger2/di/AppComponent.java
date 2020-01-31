package com.johnyhawkdesigns.a56_cwmdagger2.di;

import android.app.Application;

import com.johnyhawkdesigns.a56_cwmdagger2.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

// di = dependency injection
// Here AppComponent is a SERVICE and BaseApplication is a CLIENT
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class, // Built-in class for Injections
                ActivityBuildersModule.class, // Module to hold all Activities and Fragments
                AppModule.class, // All app level modules that we want to inject i.e. Glide,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> { // inject BaseApplication "into" AppComponent

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application); // This method is used in BaseApplication.class

        AppComponent build(); // This is like a constructor - Also used inside BaseApplication.class
    }

}
