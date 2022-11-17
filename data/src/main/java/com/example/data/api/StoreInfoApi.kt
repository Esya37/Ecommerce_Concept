package com.example.data.api

import com.example.data.entities.StoreInfoApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface StoreInfoApi {
    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getStoreInfo(): Response<StoreInfoApiResponse>
}