package com.example.pbode.brflickr;

import android.widget.SearchView;

public class stuffVM {

    public SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
}
