package com.rick.royalbank.ui.accountlist

import android.util.Log
import androidx.lifecycle.*
import com.rick.royalbank.data.model.Account
import com.rick.royalbank.data.remote.ApiResponse
import com.rick.royalbank.data.repository.AccountRepository
import kotlinx.coroutines.*

class AccountListViewModel(val repository: AccountRepository): ViewModel() {

    private val TAG = "AccountListViewModel.tag"

    var accountList: MutableLiveData<List<Account>> = MutableLiveData()

    init {
        fetchAccountList()
    }

    private fun fetchAccountList() {

        CoroutineScope(Dispatchers.IO).launch {

            val response = repository.getAccountList()

            when(response) {

                is ApiResponse.Success -> accountList.postValue(response.body)

                //TODO: Create UI element to handle response error
                is ApiResponse.Error -> {
                    Log.d(TAG, "fetchAccountList: ResponseError. " +
                            "\n Message -> ${response.errorMessage}" +
                            "\n ErrorCode -> ${response.errorCode}")
                }
            }
        }
    }
}