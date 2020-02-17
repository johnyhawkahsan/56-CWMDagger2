package com.johnyhawkdesigns.a56_cwmdagger2.di.main;

import com.johnyhawkdesigns.a56_cwmdagger2.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    // MainApi returns posts related to specific user i.e: posts?userId=1
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

}
