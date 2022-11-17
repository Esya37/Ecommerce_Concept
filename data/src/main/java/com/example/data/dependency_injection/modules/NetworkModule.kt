package com.example.data.dependency_injection.modules

import com.example.data.api.BasketApi
import com.example.data.api.PhoneInfoApi
import com.example.data.api.StoreInfoApi
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideStoreInfoApi(retrofit: Retrofit): StoreInfoApi {
        return retrofit.create(StoreInfoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBasketApi(retrofit: Retrofit): BasketApi {
        return retrofit.create(BasketApi::class.java)
    }

    @Provides
    @Singleton
    fun providePhoneInfoApi(retrofit: Retrofit): PhoneInfoApi {
        return retrofit.create(PhoneInfoApi::class.java)
    }
}