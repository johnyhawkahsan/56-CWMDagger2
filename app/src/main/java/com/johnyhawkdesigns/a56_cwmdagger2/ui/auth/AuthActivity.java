package com.johnyhawkdesigns.a56_cwmdagger2.ui.auth;

import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.johnyhawkdesigns.a56_cwmdagger2.R;
import com.johnyhawkdesigns.a56_cwmdagger2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

// https://codingwithmitch.com/courses/dagger22-android/application-component/
public class AuthActivity extends DaggerAppCompatActivity { // Instead of AppCompatActivity, we use DaggerAppCompatActivity

    private static final String TAG = AuthActivity.class.getSimpleName();

    private AuthViewModel viewModel; // We are using ViewModel to provide data to AuthActivity

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

        setLogo();

        // Initialize viewModel (NOTE: it is giving error of deprecated but it is working)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);

    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }


}
