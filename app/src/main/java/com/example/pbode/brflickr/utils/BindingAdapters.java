package com.example.pbode.brflickr.utils;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;

import dagger.BindsInstance;

import static android.support.v7.widget.SearchView.*;

public class BindingAdapters {

    @BindingAdapter("onQueryTextChanged")
    public static void setOnQueryTextChangedListener(SearchView view, OnQueryTextListener listener) {
        view.setOnQueryTextListener(listener);
    }
}
