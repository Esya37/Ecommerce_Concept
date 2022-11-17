package com.example.data.api

import com.example.data.entities.PhoneInfoApiResponse
import com.example.domain.entities.Basket
import com.example.domain.entities.PhoneInfo
import retrofit2.Response
import retrofit2.http.GET

interface PhoneInfoApi {
    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getPhoneInfo(): Response<PhoneInfoApiResponse>
}