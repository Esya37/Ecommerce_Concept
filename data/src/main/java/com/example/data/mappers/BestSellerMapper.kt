package com.example.data.mappers

import com.example.data.entities.BestSellerApiResponse
import com.example.domain.entities.BestSeller

fun BestSellerApiResponse.toBestSeller(): BestSeller {
    return BestSeller(
        isFavorites = this.isFavorites,
        title = this.title,
        priceWithoutDiscount = this.discountPrice,
        discountPrice = this.priceWithoutDiscount,
        imageSourceUrl = this.imageSourceUrl
    )
}