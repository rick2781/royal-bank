package com.rick.royalbank.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AccountApiClient {

    fun provideApi(): AccountApiService {

        val retrofit = Retrofit.Builder()
                .baseUrl(AccountApiService.ROOT_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(AccountApiService::class.java)
    }
}