package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class BasketItemApiResponse(
    val id: Int,
    @SerializedName("images")
    val imageSourceUrl: String,
    val price: Int,
    val title: String
)