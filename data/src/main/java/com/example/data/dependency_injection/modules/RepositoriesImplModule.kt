package com.example.data.dependency_injection.modules

import com.example.data.api.BasketApi
import com.example.data.api.PhoneInfoApi
import com.example.data.api.StoreInfoApi
import com.example.data.repositories.BasketRepositoryImpl
import com.example.data.repositories.PhoneInfoRepositoryImpl
import com.example.data.repositories.StoreRepositoryImpl
import com.example.domain.repositories.BasketRepository
import com.example.domain.repositories.PhoneInfoRepository
import com.example.domain.repositories.StoreRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class RepositoriesImplModule {

    @Singleton
    @Binds
    abstract fun bindBasketRepositoryImpl(basketRepository: BasketRepositoryImpl): BasketRepository

    @Singleton
    @Binds
    abstract fun bindPhoneInfoRepositoryImpl(phoneInfoRepository: PhoneInfoRepositoryImpl): PhoneInfoRepository

    @Singleton
    @Binds
    abstract fun bindStoreRepositoryImpl(storeRepository: StoreRepositoryImpl): StoreRepository
}