package com.imprarce.android.testtaskvalute.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.data.model.MoneyItem
import com.imprarce.android.testtaskvalute.utils.ApiResult
import com.imprarce.android.testtaskvalute.utils.FormatDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Fetch"

abstract class Fetch {
    private val moneyApi : RetrofitApi
    private val dateApi : RetrofitApi

    init {
        val retrofitMoney : Retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        moneyApi = retrofitMoney.create(RetrofitApi::class.java)
        dateApi = retrofitMoney.create(RetrofitApi::class.java)
    }

    fun fetchMoney() : LiveData<ApiResult<List<MoneyItem>>> {
        val responseLiveData: MutableLiveData<ApiResult<List<MoneyItem>>> = MutableLiveData()
        responseLiveData.value = ApiResult.Loading

        val flickrRequest : Call<MoneyResponse> = moneyApi.fetchMoney()

        flickrRequest.enqueue(object : Callback<MoneyResponse> {
            override fun onFailure(call: Call<MoneyResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch money", t)
                responseLiveData.value = ApiResult.Error("Failed to fetch money: ${t.message}")
            }

            override fun onResponse(call: Call<MoneyResponse>, response: Response<MoneyResponse>) {
                val moneyResponse: MoneyResponse? = response.body()
                val valuteMap: Map<String, MoneyItem>? = moneyResponse?.valute

                val moneyItemList: List<MoneyItem> = valuteMap?.values?.toList() ?: emptyList()

                val date: String = if (moneyResponse != null && !moneyResponse.date.isNullOrEmpty()) {
                    FormatDate.formatDate(moneyResponse.date)
                } else {
                    ""
                }
                val successResult = ApiResult.Success(data = moneyItemList, date = date)
                responseLiveData.value = successResult
                Log.d(TAG, "Response received money $responseLiveData")
            }
        })

        return responseLiveData
    }
}