package com.example.pbode.brflickr;

import android.support.v7.widget.SearchView;

import com.example.pbode.brflickr.service.ImageSearchProvider;

import java.util.ArrayList;

public class ImageSearchViewModel {
    private ImageSearchProvider service = new ImageSearchProvider();
    private SearchResultsAdapter adapter = new SearchResultsAdapter();

    public SearchView.OnQueryTextListener getQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getImageListForQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query.length() > 2) {
                    getImageListForQuery(query);
                }
                return false;
            }
        };
    }

    private void clearImageList() {
        adapter.setPhotoList(new ArrayList<>());
        adapter.notifyDataSetChanged();
    }

    private void getImageListForQuery(String query) {
        service.searchImages(query).subscribe(data -> {
            adapter.setPhotoList(data.getPhotoListResponse().getImageDetailsList());
            adapter.notifyDataSetChanged();
        }, Throwable::printStackTrace);
    }

    public SearchResultsAdapter getAdapter() {
        return adapter;
    }
}