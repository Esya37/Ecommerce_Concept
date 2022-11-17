package com.example.ecommerceconcept.viewmodels

import androidx.lifecycle.ViewModel
import com.example.data.repositories.PhoneInfoRepositoryImpl
import com.example.domain.entities.PhoneInfo
import com.example.domain.Result
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor(
    val phoneInfoRepositoryImpl: PhoneInfoRepositoryImpl,
) : ViewModel() {

    init {
        createMoneyFormatter()
    }

    var selectedColorIndex = 0
    var selectedSdCapacityIndex = 0

    lateinit var currentPhone: PhoneInfo
    lateinit var decimalFormat: DecimalFormat

    suspend fun getPhoneInfo(): Result<PhoneInfo> {
        return phoneInfoRepositoryImpl.getPhoneInfo()
    }

    private fun createMoneyFormatter() {
        decimalFormat = DecimalFormat("$###,##0.00")
        val symbols: DecimalFormatSymbols = decimalFormat.decimalFormatSymbols

        symbols.groupingSeparator = ','
        symbols.decimalSeparator = '.'

        decimalFormat.decimalFormatSymbols = symbols
    }
}