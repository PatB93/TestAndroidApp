package com.example.pbode.brflickr.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import javax.inject.Singleton;

@Singleton
public class UiResourceProvider {

    private Context context;

    public UiResourceProvider(Context context) {
        this.context = context;
    }

    public Drawable getImage(@DrawableRes int imageRes) {
        return context.getDrawable(imageRes);
    }

    public Drawable getDrawableFromBitmap(Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }
}
