package com.rick.royalbank.di

import com.rick.royalbank.RoyalBankApp
import com.rick.royalbank.data.remote.AccountApiClient
import com.rick.royalbank.data.remote.AccountApiService
import com.rick.royalbank.data.repository.AccountRepository
import com.rick.royalbank.ui.accountlist.AccountListAdapter
import com.rick.royalbank.ui.accountlist.AccountListViewModel
import com.rick.royalbank.utils.GenericIdlingResource
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    single { GenericIdlingResource() }
    single { AccountApiClient.provideApi() }
    factory { AccountRepository(get()) }
    factory { AccountListAdapter(get()) }

    viewModel { AccountListViewModel(get()) }
}