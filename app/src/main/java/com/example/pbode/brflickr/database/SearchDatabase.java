package com.example.pbode.brflickr.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.databinding.adapters.Converters;

@Database(entities = {SearchHistory.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class SearchDatabase extends RoomDatabase {
    public abstract SearchHistoryDao searchHistoryDao();
}