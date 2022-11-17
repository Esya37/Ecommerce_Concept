package com.example.domain.entities

import com.google.gson.annotations.SerializedName

data class BasketItem(
    val imageSourceUrl: String,
    val price: Int,
    val title: String
)