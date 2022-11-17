package com.example.data.repositories

import com.example.data.api.PhoneInfoApi
import com.example.data.mappers.toPhoneInfo
import com.example.domain.Result
import com.example.domain.entities.PhoneInfo
import com.example.domain.repositories.PhoneInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhoneInfoRepositoryImpl @Inject constructor(
    val phoneInfoApi: PhoneInfoApi
) : PhoneInfoRepository {
    override suspend fun getPhoneInfo(): Result<PhoneInfo> {
        return withContext(Dispatchers.IO) {
            val response = phoneInfoApi.getPhoneInfo()
            if (response.isSuccessful) {
                Result.Success(response.body()!!.toPhoneInfo())
            } else {
                Result.Error(Exception(response.message()))
            }
        }
    }

}