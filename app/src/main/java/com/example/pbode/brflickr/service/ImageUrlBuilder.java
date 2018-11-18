package com.example.pbode.brflickr.service;

public final class ImageUrlBuilder {
    private static final String BASE_PHOTO_URL = "https://farm%d.staticflickr.com/%s/%s_%s_%s.jpg";
    private int farm = 0;
    private String serverId = "";
    private String photoId = "";
    private String secret = "";
    private String imageType = "";

    public ImageUrlBuilder farm(int farm) {
        this.farm = farm;
        return this;
    }

    public ImageUrlBuilder serverId(String serverId) {
        this.serverId = serverId;
        return this;
    }

    public ImageUrlBuilder photoId(String photoId) {
        this.photoId = photoId;
        return this;
    }

    public ImageUrlBuilder secret(String secret) {
        this.secret = secret;
        return this;
    }

    public ImageUrlBuilder imageType(String imageType) {
        this.imageType = imageType;
        return this;
    }

    public ImageUrlBuilder thumbnail() {
        this.imageType = "t";
        return this;
    }

    public String build() {
        return String.format(BASE_PHOTO_URL, farm, serverId, photoId, secret, imageType);
    }
}
