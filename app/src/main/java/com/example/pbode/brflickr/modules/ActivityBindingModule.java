package com.example.pbode.brflickr.modules;

import com.example.pbode.brflickr.views.ImageSearchActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    abstract ImageSearchActivity provideImageSearchActivity();
}
