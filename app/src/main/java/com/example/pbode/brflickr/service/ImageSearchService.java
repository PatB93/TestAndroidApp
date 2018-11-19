package com.example.pbode.brflickr.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageSearchService {
    @GET("/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&per_page=25")
    Single<ImageSearchResponse> searchImages(@Query("api_key") String apiKey, @Query("text") String queryText);

    @GET("/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&per_page=25")
    Single<ImageSearchResponse> searchImages(@Query("api_key") String apiKey, @Query("text") String queryText, @Query("page") int page);
}