package com.johnyhawkdesigns.a56_cwmdagger2.di;


import com.johnyhawkdesigns.a56_cwmdagger2.di.auth.AuthModule;
import com.johnyhawkdesigns.a56_cwmdagger2.di.auth.AuthViewModelsModule;
import com.johnyhawkdesigns.a56_cwmdagger2.di.main.MainFragmentBuildersModule;
import com.johnyhawkdesigns.a56_cwmdagger2.di.main.MainViewModelsModule;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.auth.AuthActivity;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

// @Module are responsible for providing objects which can be injected
// Such classes can define methods annotated with @Provides
// The returned objects from these methods are available for dependency injection.
@Module
public abstract class ActivityBuildersModule {

    // We want to Add AuthActivity to our list of Activity Scope
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class, // This class provides reference of AuthViewModel to AuthActivity.
                    AuthModule.class
            }
    )
    abstract AuthActivity contributeAuthActivity(); // This method has an AuthActivity return type


    // We want to Add MainActivity to our list of Activity Scope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class, // This module holds reference to FRAGMENTS
                    MainViewModelsModule.class, // This class provides reference of ProfileViewModel +  to MainActivity
            }
    )
    abstract MainActivity contributeMainActivity(); // This method defines MainActivity's scope
}
