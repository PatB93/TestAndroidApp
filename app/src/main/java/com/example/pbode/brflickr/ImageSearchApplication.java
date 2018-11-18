package com.example.pbode.brflickr;

import android.app.Activity;
import android.app.Application;

import com.example.pbode.brflickr.modules.ApplicationModule;
import com.example.pbode.brflickr.modules.DaggerMainAndroidComponent;
import com.example.pbode.brflickr.modules.MainAndroidComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class ImageSearchApplication extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidactivityInjector;

    protected static MainAndroidComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
    }

    void buildComponent() {
        component = DaggerMainAndroidComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .application(this)
                .build();
        component.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidactivityInjector;
    }
}
