package com.johnyhawkdesigns.a56_cwmdagger2.di.main;

import com.johnyhawkdesigns.a56_cwmdagger2.network.main.MainApi;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.posts.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){ // MainApi returns posts related to specific user i.e: posts?userId=1
        return retrofit.create(MainApi.class);
    }


    @MainScope
    @Provides
    static PostRecyclerAdapter provideAdapter(){ // Injected inside PostsFragment
        return new PostRecyclerAdapter();
    }

}
