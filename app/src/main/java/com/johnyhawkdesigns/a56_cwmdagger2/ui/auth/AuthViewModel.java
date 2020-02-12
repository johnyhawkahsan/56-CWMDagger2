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
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = AuthViewModel.class.getSimpleName();

    private final AuthApi authApi;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    // This Inject method receives AuthViewModel method from "AuthViewModelsModule"
    @Inject
    public AuthViewModel(AuthApi authApi){

        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: viewmodel is wokring....");

    }

    // authenticate this user
    public void authenticateWithId(int userId){
        Log.d(TAG, "authenticateWithId: userID = " + userId );

        authUser.setValue(AuthResource.loading((User)null)); // we set value for "loading" property in AuthResource as null

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)

                // instead of calling onError, do this
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable) throws Exception {
                        User errorUser = new User(); // This is dummy ser with dummy -1 id
                        errorUser.setId(-1);
                        return errorUser;
                    }
                })

                // wrap User object in AuthResource
                .map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) throws Exception {
                        if(user.getId() == -1){ // if user is errorUser/ invalid user, then return -1
                            return AuthResource.error("Could not authenticate", null);
                        }
                        return AuthResource.authenticated(user); // if user is correct, then authenticate user
                    }
                })
                .subscribeOn(Schedulers.io()));

        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                authUser.setValue(userAuthResource);
                authUser.removeSource(source);
            }
        });
    }

    // return LiveData object from MediatorLiveData
    public LiveData<AuthResource<User>> observeUser(){
        return authUser;
    }

}
