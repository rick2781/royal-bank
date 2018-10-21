package com.rick.royalbank.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rick.royalbank.data.repository.AccountRepository
import com.rick.royalbank.di.appModule
import com.rick.royalbank.ui.accountlist.AccountListViewModel
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class AccountListViewModelTest: KoinTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(AccountRepository::class.java)
    private val viewModel = AccountListViewModel(repository)

    @Before
    fun setupKoin(){
        startKoin(listOf(appModule))
    }

    @After
    fun shutdownKoin(){
        stopKoin()
    }

    @Test
    fun testIfNull() {
        assertThat(viewModel.repository, notNullValue())
    }

    @Test
    fun changeWhenObserved() {
        viewModel.accountList
        runBlocking { verify(repository).getAccountList() }
    }
}