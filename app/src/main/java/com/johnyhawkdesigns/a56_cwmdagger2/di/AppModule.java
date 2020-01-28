package com.johnyhawkdesigns.a56_cwmdagger2.di;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    // Sample injection (string to inject into AuthActivity)
    @Provides
    static String someString(){
        return "this is a test string";
    }

}
