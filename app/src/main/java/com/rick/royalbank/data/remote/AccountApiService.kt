package com.rick.royalbank.data.remote

import androidx.lifecycle.LiveData
import com.rick.royalbank.data.model.Account
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface AccountApiService {

    companion object {
        const val ROOT_URL = "https://glacial-bayou-77287.herokuapp.com/"
    }

    @GET("listAccounts")
    fun getAccountList(): Deferred<Response<List<Account>>>
}