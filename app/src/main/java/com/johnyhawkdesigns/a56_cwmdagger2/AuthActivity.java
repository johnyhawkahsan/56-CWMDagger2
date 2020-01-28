package com.johnyhawkdesigns.a56_cwmdagger2;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.support.DaggerAppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

// https://codingwithmitch.com/courses/dagger22-android/application-component/
public class AuthActivity extends DaggerAppCompatActivity { // Instead of AppCompatActivity, we use DaggerAppCompatActivity

    private static final String TAG = AuthActivity.class.getSimpleName();

    // Just by using "Inject" annotation, we receive what "Provides" annotation which was provided inside AppModule.
    @Inject
    String testString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Log.d(TAG, "onCreate: " + testString);
    }
}
