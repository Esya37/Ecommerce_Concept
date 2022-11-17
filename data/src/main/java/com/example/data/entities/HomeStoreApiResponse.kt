package com.example.data.entities

import com.google.gson.annotations.SerializedName

data class HomeStoreApiResponse(
    val id: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    @SerializedName("picture")
    val imageSourceUrl: String,
    @SerializedName("is_buy")
    val isBuy: Boolean
)