package com.example.data.repositories

import com.example.data.api.StoreInfoApi
import com.example.data.mappers.toBestSeller
import com.example.data.mappers.toHomeStore
import com.example.domain.Result
import com.example.domain.entities.BestSeller
import com.example.domain.entities.HomeStore
import com.example.domain.repositories.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    val storeInfoApi: StoreInfoApi
) : StoreRepository {
    override suspend fun getHomeStore(): Result<List<HomeStore>> {
        return withContext(Dispatchers.IO) {
            val response = storeInfoApi.getStoreInfo()
            if (response.isSuccessful) {
                Result.Success(response.body()!!.homeStores.map { it.toHomeStore() })
            } else {
                Result.Error(Exception(response.message()))
            }
        }
    }

    override suspend fun getBestSeller(): Result<List<BestSeller>> {
        return withContext(Dispatchers.IO) {
            val response = storeInfoApi.getStoreInfo()
            if (response.isSuccessful) {
                Result.Success(response.body()!!.bestSellers.map { it.toBestSeller() })
            } else {
                Result.Error(Exception(response.message()))
            }
        }
    }

}