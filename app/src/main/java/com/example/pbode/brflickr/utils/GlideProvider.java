package com.example.pbode.brflickr.utils;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import javax.inject.Inject;

public class GlideProvider {

    private final Context context;

    @Inject
    public GlideProvider (Context context) {
        this.context = context;
    }

    public void loadUrlToTarget(String url, ObservableField<Drawable> imageView) {
        Glide.with(context)
                .load(url)
                .into(getTarget(imageView));
    }

    public SimpleTarget<Drawable> getTarget(ObservableField<Drawable> imageView) {
        return new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                imageView.set(resource);
            }
        };
    }
}
