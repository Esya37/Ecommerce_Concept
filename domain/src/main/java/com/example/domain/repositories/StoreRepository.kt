package com.example.domain.repositories

import com.example.domain.entities.BestSeller
import com.example.domain.entities.HomeStore
import com.example.domain.Result

interface StoreRepository {
    suspend fun getHomeStore(): Result<List<HomeStore>>
    suspend fun getBestSeller(): Result<List<BestSeller>>
}