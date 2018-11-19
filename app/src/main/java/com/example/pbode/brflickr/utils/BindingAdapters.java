package com.example.pbode.brflickr.utils;

import android.databinding.BindingAdapter;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.SearchView;

import static android.support.v7.widget.SearchView.OnQueryTextListener;

public class BindingAdapters {

    @BindingAdapter("onQueryTextChanged")
    public static void setOnQueryTextChangedListener(SearchView view, OnQueryTextListener listener) {
        view.setOnQueryTextListener(listener);
    }

    @BindingAdapter("setSuggestionAdapter")
    public static void setSuggestionAdapter(SearchView view, CursorAdapter cursorAdapter) {
        view.setSuggestionsAdapter(cursorAdapter);
    }
}
