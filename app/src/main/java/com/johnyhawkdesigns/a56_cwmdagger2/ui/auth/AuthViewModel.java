package com.johnyhawkdesigns.a56_cwmdagger2.ui.auth;

import android.util.Log;

import com.johnyhawkdesigns.a56_cwmdagger2.models.User;
import com.johnyhawkdesigns.a56_cwmdagger2.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = AuthViewModel.class.getSimpleName();

    private final AuthApi authApi;
    
    @Inject
    public AuthViewModel(AuthApi authApi){

        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is wokring....");

        checkIfAuthApiIsNull(authApi); // Just a test method to check if our ViewModel is working or not

        // use authApi flowable type (RxJava object) to retrieve user id
        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: " + user.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    // Just a test method to check if our ViewModel is working or not
    public void checkIfAuthApiIsNull(AuthApi authApi){
        if (this.authApi == null){
            Log.d(TAG, "AuthViewModel: auth api is NULL");
        } else {
            Log.d(TAG, "AuthViewModel: auth api is NOT NULL");
        }
    }
}
