package com.example.pbode.brflickr;

import com.example.pbode.brflickr.service.ImageDetails;

public class ImageDetailsViewModel {
    private ImageDetails imageDetails;
    private String title;

    public ImageDetailsViewModel(ImageDetails imageDetails) {
        this.imageDetails = imageDetails;
        this.title = imageDetails.getTitle();
        fetchImage();
    }

    public String getTitle() {
        return title;
    }

    private void fetchImage() {

    }
}
