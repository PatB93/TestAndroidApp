package com.example.pbode.brflickr.service

import com.google.gson.annotations.SerializedName

data class ImageSearchResponse(@SerializedName("photos") val photoListResponse: PhotoListResponse)

data class PhotoListResponse(
        @SerializedName("page") val page: Int,
        @SerializedName("pages") val pages: Int,
        @SerializedName("perPage") val perPage: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("photo") val imageDetailsList: List<ImageDetails>
)

data class ImageDetails(
        @SerializedName("id") val id: String,
        @SerializedName("owner") val owner: String,
        @SerializedName("secret") val secret: String,
        @SerializedName("server") val server: String,
        @SerializedName("farm") val farm: Int,
        @SerializedName("title") val title: String,
        @SerializedName("ispublic") val ispublic: Int,
        @SerializedName("isfriend") val isfriend: Int,
        @SerializedName("isfamily") val isfamily: Int
)
