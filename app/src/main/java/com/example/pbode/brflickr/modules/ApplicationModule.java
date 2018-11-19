package com.example.pbode.brflickr.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.pbode.brflickr.database.SearchDatabase;
import com.example.pbode.brflickr.database.SearchHistoryDao;
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

    @Provides
    public SearchHistoryDao provideSearchHistoryDao(SearchDatabase db) {
        return db.searchHistoryDao();
    }

    @Provides
    public SearchDatabase provideSearchDatabase(Context context) {
        return Room.databaseBuilder(context, SearchDatabase.class, "searchHistory.db").build();
    }
}
