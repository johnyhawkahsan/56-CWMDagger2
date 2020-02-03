package com.johnyhawkdesigns.a56_cwmdagger2.ui.auth;

import android.util.Log;

import com.johnyhawkdesigns.a56_cwmdagger2.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    private static final String TAG = AuthViewModel.class.getSimpleName();

    private final AuthApi authApi;
    
    @Inject
    public AuthViewModel(AuthApi authApi){

        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is wokring....");
        
        if (this.authApi == null){
            Log.d(TAG, "AuthViewModel: auth api is NULL");
        } else {
            Log.d(TAG, "AuthViewModel: auth api is NOT NULL");
        }
    }
}
