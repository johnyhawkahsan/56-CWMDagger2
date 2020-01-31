package com.johnyhawkdesigns.a56_cwmdagger2;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.support.DaggerAppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

// https://codingwithmitch.com/courses/dagger22-android/application-component/
public class AuthActivity extends DaggerAppCompatActivity { // Instead of AppCompatActivity, we use DaggerAppCompatActivity

    private static final String TAG = AuthActivity.class.getSimpleName();

    // @Inject annotation is used to define a dependency.
    // Just by using "Inject" annotation, we receive what "Provides" annotation provides inside "AppModule" class.
    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        setLogo();

    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }


}
