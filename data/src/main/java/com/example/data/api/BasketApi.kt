package com.example.data.api

import com.example.data.entities.BasketApiResponse
import com.example.domain.entities.Basket
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET

interface BasketApi {
    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getBasket(): Response<BasketApiResponse>
}