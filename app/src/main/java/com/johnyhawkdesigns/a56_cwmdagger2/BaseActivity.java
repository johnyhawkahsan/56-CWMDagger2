package com.johnyhawkdesigns.a56_cwmdagger2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.johnyhawkdesigns.a56_cwmdagger2.models.User;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.auth.AuthActivity;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.auth.AuthResource;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subscribeObservers();
    }


    // Subscribe observers for Base Activity
    private void subscribeObservers(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    Log.d(TAG, "onChanged: status = " + userAuthResource.status);
                    switch (userAuthResource.status){
                        case LOADING:{
                            break;
                        }

                        case AUTHENTICATED:{
                            Log.d(TAG, "onChanged: LOGIN SUCCESS: " + userAuthResource.data.getEmail());
                            break;
                        }

                        case ERROR:{
                            Log.e(TAG, "onChanged: " + userAuthResource.message + "\nDid you enter a number between 0 and 10?");
                            break;
                        }

                        case NOT_AUTHENTICATED:{
                            navLoginScreen(); // Redirect user to Login screen
                            break;
                        }
                    }
                }
            }
        });
    }

    // Redirect to Login Screen = AuthActivity
    private void navLoginScreen() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
