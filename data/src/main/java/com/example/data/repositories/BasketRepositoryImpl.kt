package com.example.data.repositories

import com.example.data.api.BasketApi
import com.example.data.mappers.toBasket
import com.example.domain.entities.Basket
import com.example.domain.repositories.BasketRepository
import kotlinx.coroutines.Dispatchers
import com.example.domain.Result
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    val basketApi: BasketApi
) : BasketRepository {
    override suspend fun getBasket(): Result<Basket> {
        return withContext(Dispatchers.IO) {
            val response = basketApi.getBasket()
            if (response.isSuccessful) {
                Result.Success(response.body()!!.toBasket())
            } else {
                Result.Error(Exception(response.message()))
            }
        }
    }

}