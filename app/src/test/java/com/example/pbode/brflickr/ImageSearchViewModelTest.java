package com.example.pbode.brflickr;

import com.example.pbode.brflickr.service.ImageDetails;
import com.example.pbode.brflickr.service.ImageSearchProvider;
import com.example.pbode.brflickr.service.ImageSearchResponse;
import com.example.pbode.brflickr.service.PhotoListResponse;
import com.example.pbode.brflickr.viewmodels.ImageSearchViewModel;
import com.example.pbode.brflickr.views.SearchResultsAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
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
    private PhotoListResponse photoListResponsePageOne;
    private ImageSearchResponse imageSearchResponsePageOne;
    private PhotoListResponse photoListResponsePageTwo;
    private ImageSearchResponse imageSearchResponsePageTwo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        subject = new ImageSearchViewModel(imageSearchProvider, adapter);
        imageDetails = new ImageDetails("", "", "", "", 1, "", 1, 1, 1);
        photoListResponsePageOne = new PhotoListResponse(1, 2, 25, 50, Collections.singletonList(imageDetails));
        imageSearchResponsePageOne = new ImageSearchResponse(photoListResponsePageOne);
        photoListResponsePageTwo = new PhotoListResponse(2, 2, 25, 50, Collections.singletonList(imageDetails));
        imageSearchResponsePageTwo = new ImageSearchResponse(photoListResponsePageTwo);
    }

    @Test
    public void getQueryTextListener_onQueryTextSubmit_makesSearchRequestAndUpdatesUi() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponsePageOne));

        subject.getQueryTextListener().onQueryTextSubmit("newText");

        verify(imageSearchProvider).searchImages("newText");
        verify(adapter).setPhotoList(imageSearchResponsePageOne.getPhotoListResponse().getImageDetailsList());
    }

    @Test
    public void getQueryTextListener_onQueryTextChangedAndLengthShorterThanThreeCharacters_makesSearchRequestAndUpdatesUi() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponsePageOne));

        subject.getQueryTextListener().onQueryTextChange("ne");

        verify(imageSearchProvider, never()).searchImages(anyString());
        assertEquals(0, subject.getAdapter().getItemCount());
    }

    @Test
    public void getQueryTextListener_onQueryTextChangedAndLengthNotShorterThanThreeCharacters_makesSearchRequestAndUpdatesUi() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponsePageOne));

        subject.getQueryTextListener().onQueryTextChange("newText");

        verify(imageSearchProvider).searchImages("newText");
        verify(adapter).setPhotoList(imageSearchResponsePageOne.getPhotoListResponse().getImageDetailsList());
    }

    @Test
    public void getNextPage_callsForNextPageAndUpdatesPageNavBar() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponsePageOne));
        when(imageSearchProvider.searchImages(anyString(), anyInt())).thenReturn(Single.just(imageSearchResponsePageTwo));
        subject.getQueryTextListener().onQueryTextChange("newText");

        subject.getNextPage();

        verify(imageSearchProvider).searchImages("newText", 2);
        assertEquals("2 / 2", subject.pagesOfTotal.get());
        assertFalse(subject.showNextButton.get());
        assertTrue(subject.showBackButton.get());
    }

    @Test
    public void getBackPage_callsForPreviousPageAndUpdatesPageNavBar() {
        when(imageSearchProvider.searchImages(anyString())).thenReturn(Single.just(imageSearchResponsePageTwo));
        when(imageSearchProvider.searchImages(anyString(), anyInt())).thenReturn(Single.just(imageSearchResponsePageOne));
        subject.getQueryTextListener().onQueryTextChange("newText");

        subject.getPreviousPage();

        verify(imageSearchProvider).searchImages("newText", 1);
        assertEquals("1 / 2", subject.pagesOfTotal.get());
        assertTrue(subject.showNextButton.get());
        assertFalse(subject.showBackButton.get());
    }
}