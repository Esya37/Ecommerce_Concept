package com.example.domain.repositories

import com.example.domain.entities.Basket
import com.example.domain.Result

interface BasketRepository {
    suspend fun getBasket(): Result<Basket>
}