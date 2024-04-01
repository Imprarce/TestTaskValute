package com.imprarce.android.testtaskvalute.data.repositoryimpl

import com.imprarce.android.testtaskvalute.data.api.RetrofitApi
import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.data.repository.RepositoryData
import retrofit2.Call

class RepositoryDataImpl( private var api : RetrofitApi) : RepositoryData {
    override suspend fun getMoney(): Call<MoneyResponse> {
        return api.fetchMoney()
    }
}