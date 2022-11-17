package com.example.ecommerceconcept.viewmodels

import androidx.lifecycle.ViewModel
import com.example.data.repositories.BasketRepositoryImpl
import com.example.domain.entities.Basket
import com.example.domain.Result
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

class MyCartViewModel @Inject constructor(
    val basketRepositoryImpl: BasketRepositoryImpl
) : ViewModel() {

    init {
        createMoneyFormatters()
    }

    lateinit var currentCart: Basket
    lateinit var decimalFormatUs: DecimalFormat
    lateinit var decimalFormatDot: DecimalFormat

    suspend fun getBasket(): Result<Basket> {
        return basketRepositoryImpl.getBasket()
    }

    private fun createMoneyFormatters() {
        createDecimalFormatterUs()
        createDecimalFormatterDot()
    }

    private fun createDecimalFormatterDot() {
        decimalFormatDot = DecimalFormat("$###0.00")
        val symbols: DecimalFormatSymbols = decimalFormatDot.decimalFormatSymbols

        symbols.groupingSeparator = ','
        symbols.decimalSeparator = '.'

        decimalFormatDot.decimalFormatSymbols = symbols
    }

    private fun createDecimalFormatterUs() {
        decimalFormatUs = DecimalFormat("$###,##0 us")
        val symbols: DecimalFormatSymbols = decimalFormatUs.decimalFormatSymbols

        symbols.groupingSeparator = ','
        symbols.decimalSeparator = '.'

        decimalFormatUs.decimalFormatSymbols = symbols
    }
}