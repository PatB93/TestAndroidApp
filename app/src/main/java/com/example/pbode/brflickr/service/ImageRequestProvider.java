package com.example.pbode.brflickr.service;

import android.graphics.Bitmap;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageRequestProvider {
    private static final String FLICKR_API_KEY = "1508443e49213ff84d566777dc211f2a";

    private ImageRequestService service;

    public ImageRequestProvider(int farm) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(getBaseUrlForFarm(farm));
        service = builder.client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImageRequestService.class);
    }

    public Single<Bitmap> getImage(ImageDetails imageDetails) {
        return Single.error(new Throwable());
    }

    public String getBaseUrlForFarm(int farm) {
        return String.format("https://farm%d.staticflickr.com", farm);
    }
}
