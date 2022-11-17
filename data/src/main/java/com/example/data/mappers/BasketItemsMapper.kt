package com.example.data.mappers

import com.example.data.entities.BasketItemApiResponse
import com.example.domain.entities.BasketItem

fun BasketItemApiResponse.toBasketItem(): BasketItem {
    return BasketItem(
        imageSourceUrl = this.imageSourceUrl,
        price = this.price,
        title = this.title
    )
}
