package com.johnyhawkdesigns.a56_cwmdagger2.ui.main.posts;

import android.util.Log;

import com.johnyhawkdesigns.a56_cwmdagger2.SessionManager;
import com.johnyhawkdesigns.a56_cwmdagger2.network.main.MainApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class PostsViewModel extends ViewModel {

    private static final String TAG = PostsViewModel.class.getSimpleName();
    
    //inject
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi){
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: viewmodel is working...");
    }

}
