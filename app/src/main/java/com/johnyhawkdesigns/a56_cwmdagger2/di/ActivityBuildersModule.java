package com.johnyhawkdesigns.a56_cwmdagger2.di;


import com.johnyhawkdesigns.a56_cwmdagger2.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity(); // This method has an AuthActivity return type

}
