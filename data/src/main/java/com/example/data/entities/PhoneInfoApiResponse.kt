package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class PhoneInfoApiResponse(
    @SerializedName("CPU")
    val cpu: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val id: String,
    @SerializedName("images")
    val imagesSourceUrls: List<String>,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String
)