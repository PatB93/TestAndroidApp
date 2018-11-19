package com.example.pbode.brflickr.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "SearchHistory")
public class SearchHistory {

    @NonNull
    @PrimaryKey
    public String search;

    public SearchHistory(String search) {
        this.search = search;
    }
}
