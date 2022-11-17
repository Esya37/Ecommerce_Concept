package com.example.domain.use_cases

import com.example.domain.repositories.PhoneInfoRepository
import javax.inject.Inject

class GetPhoneInfoUseCase @Inject constructor(private val phoneInfoRepository: PhoneInfoRepository) {
    suspend fun invoke() = phoneInfoRepository.getPhoneInfo()
}