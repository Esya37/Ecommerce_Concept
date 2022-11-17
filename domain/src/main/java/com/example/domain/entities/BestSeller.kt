package com.example.domain.entities

data class BestSeller(
    var isFavorites: Boolean,
    val title: String,
    val priceWithoutDiscount: Int,
    val discountPrice: Int,
    val imageSourceUrl: String
)