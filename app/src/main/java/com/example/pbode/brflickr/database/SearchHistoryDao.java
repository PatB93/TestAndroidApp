package com.example.pbode.brflickr.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

@Dao
public interface SearchHistoryDao {
    @Insert
    void addSearch(SearchHistory search);

    @Query("DELETE FROM SearchHistory")
    public abstract void clearHistory();

    @Query("SELECT * FROM SearchHistory WHERE search LIKE :query")
    public abstract Cursor getHistory(String query);
}