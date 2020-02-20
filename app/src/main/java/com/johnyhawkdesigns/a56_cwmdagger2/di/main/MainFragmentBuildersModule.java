package com.johnyhawkdesigns.a56_cwmdagger2.di.main;

import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.posts.PostsFragment;
import com.johnyhawkdesigns.a56_cwmdagger2.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

// This Module holds reference to our ProfileFragment and PostsFragment
@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
