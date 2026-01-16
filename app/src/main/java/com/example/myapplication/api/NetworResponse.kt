package com.example.myapplication.api

sealed class NetworResponse<out T> {
    data class Success<out T>(val data: T) : NetworResponse<T>()
    data class Error(val message: String) : NetworResponse<Nothing>()
    object Loading : NetworResponse<Nothing>()
}