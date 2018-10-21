package com.rick.royalbank.data.remote

sealed class ApiResponse<T> {

    data class Success<T>(val body: T): ApiResponse<T>()
    data class Error<T>(val errorMessage: String, val errorCode: Int): ApiResponse<T>()
}