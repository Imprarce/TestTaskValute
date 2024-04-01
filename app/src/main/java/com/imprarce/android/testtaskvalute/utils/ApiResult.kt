package com.imprarce.android.testtaskvalute.utils

import com.imprarce.android.testtaskvalute.data.model.MoneyItem

sealed class ApiResult<out T>{
    object Loading : ApiResult<Nothing>()
    data class Success<out T>(val data: List<MoneyItem>, val date: String) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}