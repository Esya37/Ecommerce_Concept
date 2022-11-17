package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class BasketApiResponse(
    @SerializedName("basket")
    val basketItemsApiResponse: List<BasketItemApiResponse>,
    val delivery: String,
    val id: String,
    val total: Int
)