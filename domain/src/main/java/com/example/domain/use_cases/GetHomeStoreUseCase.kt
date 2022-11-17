package com.example.domain.use_cases

import com.example.domain.repositories.StoreRepository
import javax.inject.Inject

class GetHomeStoreUseCase @Inject constructor(private val storeRepository: StoreRepository) {
    suspend fun invoke() = storeRepository.getHomeStore()
}