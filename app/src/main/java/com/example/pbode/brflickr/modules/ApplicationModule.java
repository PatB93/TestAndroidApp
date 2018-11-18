package com.example.pbode.brflickr.modules;

import android.app.Application;
import android.content.Context;

import com.example.pbode.brflickr.utils.UiResourceProvider;

import dagger.Module;
import dagger.Provides;

@Module()
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public UiResourceProvider privideUiResourceProvider(Context context) {
        return new UiResourceProvider(context);
    }

    @Provides
    public Context provideContext() {
        return application.getApplicationContext();
    }
}
