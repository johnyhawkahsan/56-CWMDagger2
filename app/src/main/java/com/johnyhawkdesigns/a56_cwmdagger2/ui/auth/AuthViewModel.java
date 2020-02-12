package com.johnyhawkdesigns.a56_cwmdagger2.ui.auth;

import android.util.Log;

import com.johnyhawkdesigns.a56_cwmdagger2.models.User;
import com.johnyhawkdesigns.a56_cwmdagger2.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = AuthViewModel.class.getSimpleName();

    private final AuthApi authApi;

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    // This Inject method receives AuthViewModel method from "AuthViewModelsModule"
    @Inject
    public AuthViewModel(AuthApi authApi){

        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is wokring....");

        // use authApi flowable type (RxJava object) to retrieve user id
        // authApi.getUser(1) // Not using RxJava anymore


    }


    public void authenticateWithId(int userId){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId) // get user from AuthApi
                .subscribeOn(Schedulers.io()) // do this task on background
        );

        // add first source to MediatorLiveData
        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user); // set value for MediatorLiveData
                authUser.removeSource(source); // remove source once value is set
            }
        });
    }

    // return LiveData object from MediatorLiveData
    public LiveData<User> observeUser(){
        return authUser;
    }

}
