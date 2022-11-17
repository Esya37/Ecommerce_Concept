package com.example.domain.use_cases

import com.example.domain.repositories.BasketRepository
import javax.inject.Inject

class GetBasketUseCase @Inject constructor(private val basketRepository: BasketRepository) {
    suspend fun invoke() = basketRepository.getBasket()
}