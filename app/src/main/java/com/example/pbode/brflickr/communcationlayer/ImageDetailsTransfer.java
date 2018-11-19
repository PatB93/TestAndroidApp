package com.example.pbode.brflickr.communcationlayer;

import com.example.pbode.brflickr.viewmodels.ImageDetailsViewModel;

public class ImageDetailsTransfer extends TransferObject {
    private ImageDetailsViewModel imageDetailsViewModel;

    public ImageDetailsTransfer(ImageDetailsViewModel imageDetailsViewModel) {
        this.imageDetailsViewModel = imageDetailsViewModel;
    }

    public ImageDetailsViewModel getImageDetailsViewModel() {
        return imageDetailsViewModel;
    }
}
