package com.johnyhawkdesigns.a56_cwmdagger2.di.auth;

import com.johnyhawkdesigns.a56_cwmdagger2.di.ViewModelKey;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    // This method is used/injected inside "AuthViewModel" class
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
