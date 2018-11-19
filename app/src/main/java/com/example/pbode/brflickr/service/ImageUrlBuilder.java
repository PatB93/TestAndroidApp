package com.example.pbode.brflickr.service;

public final class ImageUrlBuilder {
    private static final String SMALL_SQ = "s";
    private static final String LARGE_SQ = "q";
    private static final String THUMBNAIL = "t";
    private static final String x_SMALL = "m";
    private static final String SMALL = "n";
    private static final String MEDIUM_SMALL = "-";
    private static final String MEDIUM = "z";
    private static final String MEDIUM_LARGE = "c";
    private static final String LARGE = "b";
    private static final String X_LARGE = "h";
    private static final String XX_LARGE = "k";
    private static final String ORIGINAL = "o";

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

    public ImageUrlBuilder mediumImage() {
        this.imageType = MEDIUM;
        return this;
    }

    public ImageUrlBuilder thumbnail() {
        this.imageType = THUMBNAIL;
        return this;
    }

    public String build() {
        return String.format(BASE_PHOTO_URL, farm, serverId, photoId, secret, imageType);
    }
}
