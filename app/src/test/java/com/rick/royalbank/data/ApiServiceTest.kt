package com.rick.royalbank.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rick.royalbank.data.model.Account
import com.rick.royalbank.data.remote.AccountApiService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class ApiServiceTest {

    private lateinit var mockServer: MockWebServer
    private lateinit var apiService: AccountApiService

    @Before
    fun setupService(){

        mockServer = MockWebServer()

        apiService = Retrofit.Builder()
                .baseUrl(mockServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(AccountApiService::class.java)
    }

    @After
    fun shutdownServer() {
        mockServer.shutdown()
    }

    @Test
    fun getAccountsSuccess() {

        enqueueResponse("accountlist.json")

        var response: List<Account> = emptyList()

        runBlocking {
            response = apiService.getAccountList().await().body()!!
        }

        /**
         * Values are hardcoded because in this specific test the data will always be the same.
         */
        assertEquals(response.size, 9)
        assertEquals(response[0].roi, 13.2)
    }

    @Test
    fun getAccountsFail(){

        mockServer.enqueue(MockResponse().setResponseCode(404))

        var response: String? = null

        runBlocking {
            response = apiService.getAccountList().await().message()!!
        }

        assertEquals(response!!, "Client Error")
    }

    private fun enqueueResponse(fileName: String) {

        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)

        val source = Okio.buffer(Okio.source(inputStream))

        mockServer.enqueue(MockResponse().setBody(source.readString(Charsets.UTF_8)))
    }
}