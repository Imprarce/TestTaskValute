package com.imprarce.android.testtaskvalute.data.api

import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("daily_json.js")
    fun fetchMoney() : Call<MoneyResponse>

}