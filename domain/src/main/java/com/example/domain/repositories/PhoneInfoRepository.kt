package com.example.domain.repositories

import com.example.domain.entities.PhoneInfo
import com.example.domain.Result

interface PhoneInfoRepository {
    suspend fun getPhoneInfo(): Result<PhoneInfo>
}