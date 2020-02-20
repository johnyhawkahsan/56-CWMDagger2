package com.johnyhawkdesigns.a56_cwmdagger2.di;

import com.johnyhawkdesigns.a56_cwmdagger2.viewmodels.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

// This module will only be scoped to AuthComponent
@Module
abstract public class ViewModelFactoryModule {

    // @Binds is a little different from @Provides - 1st it uses abstract and 2nd, it does not require method body, while Provides requires method body.
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}
