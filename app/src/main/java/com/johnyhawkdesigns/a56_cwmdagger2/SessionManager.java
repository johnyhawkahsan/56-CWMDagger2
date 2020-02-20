package com.johnyhawkdesigns.a56_cwmdagger2;

import android.util.Log;

import com.johnyhawkdesigns.a56_cwmdagger2.models.User;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


/**
 * SessionManager lasts through out the app and is responsible for logged in user's session data
 */
@Singleton
public class SessionManager {

    private static final String TAG = SessionManager.class.getSimpleName();

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    // Authenticate user with the provided id
    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        if (cachedUser != null) {

            cachedUser.setValue(AuthResource.loading((User) null)); // set value for MediatorLiveData cachedUser as loading status = // we set value for "loading" property in AuthResource as null

            cachedUser.addSource(source, new Observer<AuthResource<User>>() { // add source for MediatorLiveData cachedUser
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource); // set value for MediatorLiveData cachedUser = userAuthResource
                    cachedUser.removeSource(source); // stop listening to the given LiveData
                }
            });
        }
    }


    // set logout value in cached user
    public void logout(){
        Log.d(TAG, "logout: logging out...");
        cachedUser.setValue(AuthResource.logout());
    }

    // method to return cached user object - NOTE: This method is used by AuthViewModel's method "observeAuthState"
    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }

}
