package com.example.pbode.brflickr.viewmodels;

import android.support.v7.widget.SearchView;

import com.example.pbode.brflickr.views.SearchResultsAdapter;
import com.example.pbode.brflickr.service.ImageSearchProvider;

import java.util.ArrayList;

import javax.inject.Inject;

public class ImageSearchViewModel {
    private ImageSearchProvider imageSearchProvider;
    private SearchResultsAdapter adapter;

    @Inject
    public ImageSearchViewModel(ImageSearchProvider imageSearchProvider, SearchResultsAdapter adapter) {
        this.imageSearchProvider = imageSearchProvider;
        this.adapter = adapter;
        adapter.setImageSearchViewModel(this);
    }

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
        imageSearchProvider.searchImages(query).subscribe(data -> {
            adapter.setPhotoList(data.getPhotoListResponse().getImageDetailsList());
            adapter.notifyDataSetChanged();
        }, Throwable::printStackTrace);
    }

    public SearchResultsAdapter getAdapter() {
        return adapter;
    }
}