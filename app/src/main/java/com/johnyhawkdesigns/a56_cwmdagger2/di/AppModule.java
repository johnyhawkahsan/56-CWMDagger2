package com.johnyhawkdesigns.a56_cwmdagger2.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.johnyhawkdesigns.a56_cwmdagger2.R;


import javax.inject.Singleton;

import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;

// @Module are responsible for providing objects which can be injected
// Such classes can define methods annotated with @Provides
// The returned objects from these methods are available for dependency injection.
@Module
public class AppModule {


    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }

}
