package com.rick.royalbank.data.repository

import com.rick.royalbank.data.model.Account
import com.rick.royalbank.data.remote.*

class AccountRepository(
        private val accountApiService: AccountApiService) {

    suspend fun getAccountList(): ApiResponse<List<Account>> {

        val apiResponse = accountApiService.getAccountList().await()

        return if (apiResponse.isSuccessful)
            ApiResponse.Success(apiResponse.body()!!)
        else ApiResponse.Error(apiResponse.message(), apiResponse.code())
    }
}