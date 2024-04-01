package com.imprarce.android.testtaskvalute.utils

sealed class ApiResult<out T>{
    object Loading : ApiResult<Nothing>()
    data class Success<out T>(val data: T, val date: String) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}