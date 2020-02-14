package com.johnyhawkdesigns.a56_cwmdagger2.ui.main.profile;

import android.util.Log;

import com.johnyhawkdesigns.a56_cwmdagger2.SessionManager;
import com.johnyhawkdesigns.a56_cwmdagger2.models.User;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.auth.AuthResource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = ProfileViewModel.class.getSimpleName();

    private SessionManager sessionManager;
    
    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        Log.d(TAG, "ProfileViewModel: viewmodel is ready..");

        this.sessionManager = sessionManager;
    }

    // return LiveData object from SessionManager's MediatorLiveData cachedUser
    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
}
