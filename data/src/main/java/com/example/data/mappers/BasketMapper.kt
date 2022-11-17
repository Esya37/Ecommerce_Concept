package com.example.data.mappers

import com.example.data.entities.BasketApiResponse
import com.example.data.entities.BasketItemApiResponse
import com.example.domain.entities.Basket

fun BasketApiResponse.toBasket(): Basket {
    return Basket(
        basketItems = this.basketItemsApiResponse.map {
            it.toBasketItem()
        },
        delivery = this.delivery,
        id = this.id,
        total = this.total
    )
}