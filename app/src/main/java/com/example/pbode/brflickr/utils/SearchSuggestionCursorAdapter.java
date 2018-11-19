package com.example.pbode.brflickr.utils;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.CursorAdapter;
import android.widget.TextView;

import com.example.pbode.brflickr.R;

import javax.inject.Inject;

public class SearchSuggestionCursorAdapter extends CursorAdapter {

    public SearchSuggestionCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.suggestion_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.suggestion);
        textView.setText(cursor.getString(cursor.getColumnIndexOrThrow("search")));
    }

    public static class Factory {

        private Context context;

        @Inject
        public Factory(Context context) {
            this.context = context;
        }

        public SearchSuggestionCursorAdapter newInstance(Cursor cursor) {
            return new SearchSuggestionCursorAdapter(context, cursor);
        }
    }
}
