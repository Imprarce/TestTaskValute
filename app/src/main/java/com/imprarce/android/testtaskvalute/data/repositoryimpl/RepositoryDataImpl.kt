package com.imprarce.android.testtaskvalute.data.repositoryimpl

import com.imprarce.android.testtaskvalute.data.api.RetrofitApi
import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.data.repository.RepositoryData
import retrofit2.Call

class RepositoryDataImpl( private var api : RetrofitApi) : RepositoryData {
    override suspend fun getMoney(): Call<MoneyResponse> {
        return api.fetchMoney()

//        if (response.isSuccessful) {
//            return response.body() ?: throw Exception("Response body is null")
//        } else {
//            throw Exception("Failed to fetch money: ${response.errorBody()?.string()}")
//        }
    }
}