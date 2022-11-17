package com.example.data.entities

import com.example.domain.entities.BestSeller
import com.example.domain.entities.HomeStore
import com.google.gson.annotations.SerializedName

data class StoreInfoApiResponse(
    @SerializedName("home_store")
    val homeStores: List<HomeStoreApiResponse>,
    @SerializedName("best_seller")
    val bestSellers: List<BestSellerApiResponse>
)