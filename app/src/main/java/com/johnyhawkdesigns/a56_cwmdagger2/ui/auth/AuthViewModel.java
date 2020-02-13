package com.johnyhawkdesigns.a56_cwmdagger2.ui.auth;

import android.util.Log;

import com.johnyhawkdesigns.a56_cwmdagger2.SessionManager;
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

    // inject
    private final AuthApi authApi;
    private SessionManager sessionManager;

    // This Inject method receives AuthViewModel method from "AuthViewModelsModule"
    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager){
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: viewmodel is working....");
    }

    // authenticate this user
    public void authenticateWithId(int userId){
        Log.d(TAG, "authenticateWithId: attempting to login." );
        sessionManager.authenticateWithId(queryUserId(userId)); //
    }

    // Method to query user id from internet
    private LiveData<AuthResource<User>> queryUserId(int userId){
        Log.d(TAG, "queryUserId: userId = " + userId);

        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId) // this is our source/publisher from where we want to get the user

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
    }

    // return LiveData object from SessionManager's MediatorLiveData cachedUser
    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }

}
