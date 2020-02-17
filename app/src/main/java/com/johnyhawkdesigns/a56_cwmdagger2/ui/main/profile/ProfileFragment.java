package com.johnyhawkdesigns.a56_cwmdagger2.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.johnyhawkdesigns.a56_cwmdagger2.R;
import com.johnyhawkdesigns.a56_cwmdagger2.models.User;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.auth.AuthResource;
import com.johnyhawkdesigns.a56_cwmdagger2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();

    private ProfileViewModel profileViewModel;
    private TextView email, username, website;

    @Inject
    ViewModelProviderFactory providerFactory;

    // You should inflate your layout in onCreateView but shouldn't initialize other views using findViewById in onCreateView.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Log.d(TAG, "onCreateView: Profile Fragment");
        return view;
    }

    // always use findViewById in onViewCreated(when view is fully created) because in onCreateView sometimes view is not properly initialized.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ProfileFragment was created...");
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);

        profileViewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class); // Instantiate profileViewModel

        subscribeObservers();
    }

    private void subscribeObservers(){
        profileViewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner()); // we remove in Fragment, because we want to make sure previous observers are destroyed.
        profileViewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    Log.d(TAG, "onChanged: status = " + userAuthResource.status);

                    switch (userAuthResource.status){

                        case AUTHENTICATED:{
                            Log.d(TAG, "onChanged: ProfileFragment: AUTHENTICATED... " +
                                    "/nAuthenticated as: " + userAuthResource.data.getEmail());
                            setUserDetails(userAuthResource.data);
                            break;
                        }

                        case ERROR:{
                            Log.d(TAG, "onChanged: ProfileFragment: ERROR...");
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message){
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }

    private void setUserDetails(User user){
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        website.setText(user.getWebsite());
    }
}
