package com.johnyhawkdesigns.a56_cwmdagger2.di.main;

import com.johnyhawkdesigns.a56_cwmdagger2.di.ViewModelKey;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.profile.ProfileViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

}
