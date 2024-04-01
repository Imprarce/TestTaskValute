package com.imprarce.android.testtaskvalute.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imprarce.android.testtaskvalute.data.api.response.DateResponse
import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.data.model.MoneyItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Fetch"

class Fetch {
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

    fun fetchMoney() : LiveData<List<MoneyItem>> {
        val responseLiveData: MutableLiveData<List<MoneyItem>> = MutableLiveData()
        val flickrRequest : Call<MoneyResponse> = moneyApi.fetchMoney()

        flickrRequest.enqueue(object : Callback<MoneyResponse> {
            override fun onFailure(call: Call<MoneyResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch money", t)
            }

            override fun onResponse(call: Call<MoneyResponse>, response: Response<MoneyResponse>) {
                val moneyResponse: MoneyResponse? = response.body()
                val valuteMap: Map<String, MoneyItem>? = moneyResponse?.valute

                val moneyItemList: List<MoneyItem> = valuteMap?.values?.toList() ?: emptyList()

                responseLiveData.value = moneyItemList
                Log.d(TAG, "Response received money ${responseLiveData.value}")
            }
        })

        return responseLiveData
    }

    fun fetchDate() : LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val flickrRequest : Call<DateResponse> = dateApi.fetchDate()

        flickrRequest.enqueue(object : Callback<DateResponse> {
            override fun onFailure(call: Call<DateResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch date", t)
            }

            override fun onResponse(call: Call<DateResponse>, response: Response<DateResponse>) {
                val dateResponse: DateResponse? = response.body()

                val date: String= dateResponse?.date ?: ""

                responseLiveData.value = date
                Log.d(TAG, "Response received date ${responseLiveData.value}")
            }
        })

        return responseLiveData
    }
}