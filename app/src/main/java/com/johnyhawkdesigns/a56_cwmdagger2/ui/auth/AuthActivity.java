package com.johnyhawkdesigns.a56_cwmdagger2.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.johnyhawkdesigns.a56_cwmdagger2.R;
import com.johnyhawkdesigns.a56_cwmdagger2.models.User;
import com.johnyhawkdesigns.a56_cwmdagger2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

// https://codingwithmitch.com/courses/dagger22-android/application-component/
public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener{ // Instead of AppCompatActivity, we use DaggerAppCompatActivity

    private static final String TAG = AuthActivity.class.getSimpleName();

    private AuthViewModel viewModel; // We are using ViewModel to provide data to AuthActivity

    private EditText userId;
    private ProgressBar progressBar;

    // @Inject annotation is used to define a dependency - Just by using "Inject" annotation, we receive what "Provides" annotation provides inside "AppModule" class.
    @Inject
    ViewModelProviderFactory viewModelProviderFactory; // ViewModelFactoryModule = ViewModelProvider.Factory bindViewModelFactory()

    @Inject
    Drawable logo; // AppModule = Provided by Drawable provideAppDrawable(Application application)

    @Inject
    RequestManager requestManager; // AppModule = Provided by RequestManager provideGlideInstance



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);

        // Initialize viewModel (NOTE: it is giving error of deprecated but it is working)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();
    }

    // observeUser() will return authUser which is of type MediatorLiveData
    private void subscribeObservers() {
        viewModel.observeUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    Log.d(TAG, "onChanged: status = " + userAuthResource.status);
                    switch (userAuthResource.status){
                        case LOADING:{
                            showProgressBar(false);
                            break;
                        }

                        case AUTHENTICATED:{
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS: " + userAuthResource.data.getEmail());
                            break;
                        }

                        case ERROR:{
                            showProgressBar(false);
                            Log.e(TAG, "onChanged: " + userAuthResource.message);
                            Toast.makeText(AuthActivity.this,
                                    userAuthResource.message + "\nDid you enter a number between 0 and 10?",
                                    Toast.LENGTH_SHORT)
                                    .show();
                            break;
                        }

                        case NOT_AUTHENTICATED:{
                            showProgressBar(false);
                            break;
                        }
                    }
                }
            }
        });
    }


    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }


    // do these when button is clicked
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.login_button:{
                attemptLogin();
                break;
            }
        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));

    }

    private void showProgressBar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

}
