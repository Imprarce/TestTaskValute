package com.imprarce.android.testtaskvalute.data.api

import android.util.Log
import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.data.model.MoneyItem
import com.imprarce.android.testtaskvalute.utils.ApiResult
import com.imprarce.android.testtaskvalute.utils.FormatDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Fetch"

@Suppress("UNCHECKED_CAST")
abstract class Fetch {
    private val moneyApi : RetrofitApi

    init {
        val retrofitMoney : Retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        moneyApi = retrofitMoney.create(RetrofitApi::class.java)
    }

    suspend fun <T> fetchMoney(function: suspend () -> Call<MoneyResponse>): ApiResult<T> {
        try {
            val response = withContext(Dispatchers.IO) {
                function().execute()
            }
            if (response.isSuccessful) {
                val moneyResponse: MoneyResponse? = response.body()
                val valuteMap: Map<String, MoneyItem>? = moneyResponse?.valute
                val moneyItemList: List<MoneyItem> = valuteMap?.values?.toList() ?: emptyList()
                val date: String = if (moneyResponse != null && !moneyResponse.date.isNullOrEmpty()) {
                    FormatDate.formatDate(moneyResponse.date)
                } else {
                    ""
                }
                return ApiResult.Success(data = moneyItemList, date = date)
            } else {
                Log.e(TAG, "${response.message()}")
                return ApiResult.Error("Failed to fetch money: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.e(TAG, "${ex.message}")
            return ApiResult.Error("Failed to fetch money: ${ex.message}")
        }
    }

}