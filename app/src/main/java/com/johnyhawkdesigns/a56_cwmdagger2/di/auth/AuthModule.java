package com.johnyhawkdesigns.a56_cwmdagger2.di.auth;

import com.johnyhawkdesigns.a56_cwmdagger2.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class AuthModule {

    @AuthScope // After defining AuthScope, we used it here.
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
}
