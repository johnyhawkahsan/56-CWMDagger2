package com.johnyhawkdesigns.a56_cwmdagger2.di;


import com.johnyhawkdesigns.a56_cwmdagger2.di.auth.AuthModule;
import com.johnyhawkdesigns.a56_cwmdagger2.di.auth.AuthScope;
import com.johnyhawkdesigns.a56_cwmdagger2.di.auth.AuthViewModelsModule;
import com.johnyhawkdesigns.a56_cwmdagger2.di.main.MainFragmentBuildersModule;
import com.johnyhawkdesigns.a56_cwmdagger2.di.main.MainModule;
import com.johnyhawkdesigns.a56_cwmdagger2.di.main.MainScope;
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


    @AuthScope
    // We defined AuthScope in di->auth->AuthScope = We want to Add AuthActivity to our list of Activity Scope
    @ContributesAndroidInjector( // When we use "ContributesAndroidInjector", code is generated java(generated) -> di -> ActivityBuildersModule_ContributeAuthActivity
            modules = {
                    AuthViewModelsModule.class, // This class provides reference of AuthViewModel to AuthActivity.
                    AuthModule.class // holds reference to AuthApi which holds query to retrieve specific user
            }
    )
    abstract AuthActivity contributeAuthActivity(); // AuthActivity is the 1st SUB-COMPONENT


    @MainScope // We defined MainScope in di->main->MainScope
    @ContributesAndroidInjector(  // When we use "ContributesAndroidInjector", code is generated java(generated) -> di ->ActivityBuildersModule_ContributeMainActivity
            modules = {
                    MainFragmentBuildersModule.class, // This module holds reference to FRAGMENTS
                    MainViewModelsModule.class, // This class provides reference of ProfileViewModel +  to MainActivity
                    MainModule.class, // This class holds reference to "MainApi" which is retrofit query for "posts" of specific user
            }
    )
    abstract MainActivity contributeMainActivity(); // MainActivity is the 2nd SUB-COMPONENT
}