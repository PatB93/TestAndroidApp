package com.example.pbode.brflickr;

import com.example.pbode.brflickr.service.ImageDetails;
import com.example.pbode.brflickr.service.ImageSearchProvider;
import com.example.pbode.brflickr.service.ImageSearchResponse;
import com.example.pbode.brflickr.service.PhotoListResponse;
import com.example.pbode.brflickr.viewmodels.ImageSearchViewModel;
import com.example.pbode.brflickr.views.SearchResultsAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageSearchViewModelTest {

    @Mock
    private ImageSearchProvider imageSearchProvider;

    @Mock
    private SearchResultsAdapter adapter;

    private ImageSearchViewModel subject;
    private ImageDetails imageDetails;
    private PhotoListResponse photoListResponse;
    private ImageSearchResponse imageSearchResponse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        subject = new ImageSearchViewModel(imageSearchProvider, adapter);
        imageDetails = new ImageDetails("", "", "", "", 1, "", 1, 1, 1);
        photoListResponse = new PhotoListResponse(1, 1, 25, 1, Collections.singletonList(imageDetails));
        imageSearchResponse = new ImageSearchResponse(photoListResponse);
    }

    @Test
    public void getQueryTextListener_onQueryTextSubmit_makesSearchRequestAndUpdatesUi() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponse));

        subject.getQueryTextListener().onQueryTextSubmit("newText");

        verify(imageSearchProvider).searchImages("newText");
        verify(adapter).setPhotoList(imageSearchResponse.getPhotoListResponse().getImageDetailsList());
    }

    @Test
    public void getQueryTextListener_onQueryTextChangedAndQueryIsEmptyAfterSearching_makesSearchRequestAndUpdatesUi() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponse));

        subject.getQueryTextListener().onQueryTextChange("ne");

        verify(imageSearchProvider, never()).searchImages(anyString());
        assertEquals(0, subject.getAdapter().getItemCount());
    }

    @Test
    public void getQueryTextListener_onQueryTextChangedAndLengthShorterThanThreeCharacters_makesSearchRequestAndUpdatesUi() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponse));

        subject.getQueryTextListener().onQueryTextChange("newText");
        reset(imageSearchProvider);
        subject.getQueryTextListener().onQueryTextChange("ne");

        verify(imageSearchProvider, never()).searchImages(anyString());
        assertEquals(0, subject.getAdapter().getItemCount());
    }

    @Test
    public void getQueryTextListener_onQueryTextChangedAndLengthNotShorterThanThreeCharacters_makesSearchRequestAndUpdatesUi() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponse));

        subject.getQueryTextListener().onQueryTextChange("newText");

        verify(imageSearchProvider).searchImages("newText");
        verify(adapter).setPhotoList(imageSearchResponse.getPhotoListResponse().getImageDetailsList());
    }
}