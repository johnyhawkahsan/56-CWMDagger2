package com.johnyhawkdesigns.a56_cwmdagger2.viewmodels;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// NOTE: This class is created to overcome some problem which is mentioned by Mitch in the following video
// https://codingwithmitch.com/courses/dagger22-android/injecting-viewmodels-dagger2-android-map-multibind/
public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private static final String TAG = ViewModelProviderFactory.class.getSimpleName();

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    // 1st parameter = Class<? extends ViewModel> means any class that extends ViewModel
    // 2nd parameter = Provider<ViewModel> - Any provider of ViewModel
    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) { // if the viewmodel has not been created

            // loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {

                // if it's allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        // if this is not one of the allowed keys, throw exception
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }

        // return the Provider
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}