package com.example.ecommerceconcept.viewmodels

import androidx.lifecycle.ViewModel
import com.example.data.repositories.BasketRepositoryImpl
import com.example.data.repositories.PhoneInfoRepositoryImpl
import com.example.data.repositories.StoreRepositoryImpl
import com.example.domain.entities.BestSeller
import com.example.domain.entities.HomeStore
import com.example.domain.Result
import javax.inject.Inject

class MainScreenFragmentViewModel @Inject constructor(
    val storeRepositoryImpl: StoreRepositoryImpl
) : ViewModel() {

    var selectedCategoryIndex = 0
    lateinit var homeStore: List<HomeStore>
    lateinit var bestSellers: List<BestSeller>

    suspend fun getHomeStore(): Result<List<HomeStore>> {
        return storeRepositoryImpl.getHomeStore()
    }

    suspend fun getBestSellers(): Result<List<BestSeller>> {
        return storeRepositoryImpl.getBestSeller()
    }

}