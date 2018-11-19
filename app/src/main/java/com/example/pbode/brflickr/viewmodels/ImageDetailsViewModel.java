package com.example.pbode.brflickr.viewmodels;

import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;

import com.example.pbode.brflickr.R;
import com.example.pbode.brflickr.service.ImageDetails;
import com.example.pbode.brflickr.service.ImageUrlBuilder;
import com.example.pbode.brflickr.utils.GlideProvider;
import com.example.pbode.brflickr.utils.UiResourceProvider;

import javax.inject.Inject;

public class ImageDetailsViewModel {
    public ObservableField<Drawable> thumbnail = new ObservableField<>();
    public ObservableField<Drawable> fullImage = new ObservableField<>();
    private String title;
    private GlideProvider glideProvider;

    public ImageDetailsViewModel(ImageDetails imageDetails, GlideProvider glideProvider, UiResourceProvider resourceProvider) {
        this.title = imageDetails.getTitle();
        this.glideProvider = glideProvider;
        thumbnail.set(resourceProvider.getImage(R.drawable.ic_image_not_found));
        fullImage.set(resourceProvider.getImage(R.drawable.ic_image_not_found));
        setThumbnailImageFromUrl(imageDetails, thumbnail);
        setFullImageFromUrl(imageDetails, fullImage);
    }

    public String getTitle() {
        return title;
    }

    public void fetchImage(String imageUrl, ObservableField<Drawable> targetImage) {
        glideProvider.loadUrlToTarget(imageUrl, targetImage);
    }

    private void setThumbnailImageFromUrl(ImageDetails imageDetails, ObservableField<Drawable> imageField) {
        ImageUrlBuilder imageUrlBuilder = new ImageUrlBuilder();
        String url = imageUrlBuilder
                .farm(imageDetails.getFarm())
                .serverId(imageDetails.getServer())
                .photoId(imageDetails.getId())
                .secret(imageDetails.getSecret())
                .thumbnail()
                .build();
        fetchImage(url, imageField);
    }

    private void setFullImageFromUrl(ImageDetails imageDetails, ObservableField<Drawable> imageField) {
        ImageUrlBuilder imageUrlBuilder = new ImageUrlBuilder();
        String url = imageUrlBuilder
                .farm(imageDetails.getFarm())
                .serverId(imageDetails.getServer())
                .photoId(imageDetails.getId())
                .secret(imageDetails.getSecret())
                .mediumImage()
                .build();
        fetchImage(url, imageField);
    }

    public static class Factory {
        private GlideProvider glideProvider;
        private UiResourceProvider resourceProvider;

        @Inject
        public Factory(GlideProvider glideProvider, UiResourceProvider resourceProvider) {
            this.glideProvider = glideProvider;
            this.resourceProvider = resourceProvider;
        }

        public ImageDetailsViewModel newInstance(ImageDetails imageDetails) {
            return new ImageDetailsViewModel(imageDetails, glideProvider, resourceProvider);
        }
    }
}
