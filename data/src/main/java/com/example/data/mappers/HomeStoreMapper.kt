package com.example.data.mappers

import com.example.data.entities.HomeStoreApiResponse
import com.example.domain.entities.HomeStore

fun HomeStoreApiResponse.toHomeStore(): HomeStore {
    return HomeStore(
        isNew = this.isNew,
        title = this.title,
        subtitle = this.subtitle,
        imageSourceUrl = this.imageSourceUrl,
        isBuy = this.isBuy
    )
}