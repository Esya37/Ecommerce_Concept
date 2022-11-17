package com.example.domain.entities

import com.google.gson.annotations.SerializedName

data class HomeStore(
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val imageSourceUrl: String,
    val isBuy: Boolean
)