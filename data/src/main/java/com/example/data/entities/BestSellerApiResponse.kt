package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class BestSellerApiResponse(
    val id: Int,
    @SerializedName("is_favorites")
    val isFavorites: Boolean,
    val title: String,
    @SerializedName("price_without_discount")
    val priceWithoutDiscount: Int,
    @SerializedName("discount_price")
    val discountPrice: Int,
    @SerializedName("picture")
    val imageSourceUrl: String
)