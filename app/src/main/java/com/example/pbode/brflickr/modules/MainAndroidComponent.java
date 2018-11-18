package com.example.pbode.brflickr.modules;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.example.pbode.brflickr.ImageSearchApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class
})
public interface MainAndroidComponent extends AndroidInjector<ImageSearchApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder applicationModule(ApplicationModule applicationModule);

        MainAndroidComponent build();
    }

    void inject(AppCompatActivity activity);
}
