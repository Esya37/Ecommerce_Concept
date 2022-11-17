package com.example.domain.entities

data class PhoneInfo(
    val cpu: String,
    val camera: String,
    var capacity: List<String>,
    var color: List<String>,
    val imagesSourceUrls: List<String>,
    var isFavorites: Boolean,
    val price: Int,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String
)