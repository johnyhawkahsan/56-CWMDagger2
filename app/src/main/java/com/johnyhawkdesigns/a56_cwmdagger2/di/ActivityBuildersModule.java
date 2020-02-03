package com.johnyhawkdesigns.a56_cwmdagger2.di;


import com.johnyhawkdesigns.a56_cwmdagger2.di.auth.AuthModule;
import com.johnyhawkdesigns.a56_cwmdagger2.di.auth.AuthViewModelsModule;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

// @Module are responsible for providing objects which can be injected
// Such classes can define methods annotated with @Provides
// The returned objects from these methods are available for dependency injection.
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
                    AuthModule.class
            }
    )
    abstract AuthActivity contributeAuthActivity(); // This method has an AuthActivity return type

}
