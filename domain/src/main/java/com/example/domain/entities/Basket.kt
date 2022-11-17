package com.example.domain.entities

import com.google.gson.annotations.SerializedName

data class Basket(
    val basketItems: List<BasketItem>,
    val delivery: String,
    val id: String,
    val total: Int
)