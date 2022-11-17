package com.example.data.mappers

import com.example.data.entities.PhoneInfoApiResponse
import com.example.domain.entities.PhoneInfo

fun PhoneInfoApiResponse.toPhoneInfo(): PhoneInfo {
    return PhoneInfo(
        cpu = this.cpu,
        camera = this.camera,
        capacity = this.capacity,
        color = this.color,
        imagesSourceUrls = this.imagesSourceUrls,
        isFavorites = this.isFavorites,
        price = this.price,
        rating = this.rating,
        sd = this.sd,
        ssd = this.ssd,
        title = this.title
    )
}