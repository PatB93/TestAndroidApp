package com.example.pbode.brflickr.service;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageSearchProvider {
    private static final String FLICKR_SEARCH_URL = "https://api.flickr.com";
    private static final String FLICKR_API_KEY = "1508443e49213ff84d566777dc211f2a";

    private ImageSearchService service;

    @Inject
    public ImageSearchProvider() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(FLICKR_SEARCH_URL);
        service = builder.client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImageSearchService.class);
    }

    public Single<ImageSearchResponse> searchImages(String query) {
        return service.searchImages(FLICKR_API_KEY, query)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ImageSearchResponse> searchImages(String query, int page) {
        return service.searchImages(FLICKR_API_KEY, query, page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
