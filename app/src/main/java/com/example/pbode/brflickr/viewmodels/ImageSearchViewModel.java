package com.example.pbode.brflickr.viewmodels;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.SearchView;
import com.example.pbode.brflickr.database.SearchHistory;
import com.example.pbode.brflickr.database.SearchHistoryDao;
import com.example.pbode.brflickr.service.PhotoListResponse;
import com.example.pbode.brflickr.utils.SearchSuggestionCursorAdapter;
import com.example.pbode.brflickr.views.SearchResultsAdapter;
import com.example.pbode.brflickr.service.ImageSearchProvider;

import java.util.ArrayList;

import javax.inject.Inject;

public class ImageSearchViewModel {
    public ObservableBoolean showPageSelectionBar = new ObservableBoolean(false);
    public ObservableBoolean showBackButton = new ObservableBoolean(false);
    public ObservableBoolean showNextButton = new ObservableBoolean(false);
    public ObservableField<String> pagesOfTotal = new ObservableField<>("");

    private final ImageSearchProvider imageSearchProvider;
    private final SearchResultsAdapter adapter;
    private final SearchHistoryDao searchHistoryDao;
    private final SearchSuggestionCursorAdapter.Factory searchSuggestionCursorAdapterFactory;
    private PhotoListResponse photoListResponse;
    private int currentPage = 0;
    private String query = "";

    @Inject
    public ImageSearchViewModel(ImageSearchProvider imageSearchProvider, SearchResultsAdapter adapter, SearchHistoryDao searchHistoryDao, SearchSuggestionCursorAdapter.Factory searchSuggestionCursorAdapterFactory) {
        this.imageSearchProvider = imageSearchProvider;
        this.adapter = adapter;
        this.searchHistoryDao = searchHistoryDao;
        this.searchSuggestionCursorAdapterFactory = searchSuggestionCursorAdapterFactory;
        adapter.setImageSearchViewModel(this);
    }

    public SearchView.OnQueryTextListener getQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getImageListForQuery(query);
                searchHistoryDao.addSearch(new SearchHistory(query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query.isEmpty()) {
                    clearImageList();
                } if (query.length() > 2) {
                    getImageListForQuery(query);
                }
                return false;
            }
        };
    }

    public CursorAdapter getSuggestionAdapter() {
        return searchSuggestionCursorAdapterFactory.newInstance(searchHistoryDao.getHistory("dog"));
    }

    public void getNextPage() {
        imageSearchProvider.searchImages(query, currentPage + 1).subscribe(data -> {
            photoListResponse = data.getPhotoListResponse();
            currentPage = photoListResponse.getPage();
            adapter.setPhotoList(photoListResponse.getImageDetailsList());
            setUpPageNavigaion();
            adapter.notifyDataSetChanged();
        }, Throwable::printStackTrace);
    }

    public void getPreviousPage() {
        imageSearchProvider.searchImages(query, currentPage - 1).subscribe(data -> {
            photoListResponse = data.getPhotoListResponse();
            currentPage = photoListResponse.getPage();
            adapter.setPhotoList(photoListResponse.getImageDetailsList());
            setUpPageNavigaion();
            adapter.notifyDataSetChanged();
        }, Throwable::printStackTrace);
    }

    private void clearImageList() {
        showPageSelectionBar.set(false);
        showBackButton.set(false);
        showNextButton.set(false);
        adapter.setPhotoList(new ArrayList<>());
        adapter.notifyDataSetChanged();
    }

    private void getImageListForQuery(String query) {
        this.query = query;
        imageSearchProvider.searchImages(query).subscribe(data -> {
            photoListResponse = data.getPhotoListResponse();
            currentPage = photoListResponse.getPage();
            adapter.setPhotoList(photoListResponse.getImageDetailsList());
            setUpPageNavigaion();
            adapter.notifyDataSetChanged();
        }, Throwable::printStackTrace);
    }

    private void setUpPageNavigaion() {
        showPageSelectionBar.set(photoListResponse.getImageDetailsList().size() > 1);
        showBackButton.set(currentPage != 1);
        showNextButton.set(currentPage != photoListResponse.getTotalPages());
        pagesOfTotal.set(String.format("%d / %d", photoListResponse.getPage(), photoListResponse.getTotalPages()));
    }

    public SearchResultsAdapter getAdapter() {
        return adapter;
    }
}