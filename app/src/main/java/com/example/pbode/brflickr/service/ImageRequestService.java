package com.example.pbode.brflickr.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImageRequestService {
    @GET("/{server_id}/{id}_{secret}.jpg")
    Single<ImageSearchResponse> searchImages(@Path("server_id") String serverId, @Path("id") String imageId, @Path("secret") String secret);
}