package com.imprarce.android.testtaskvalute.data.repository

import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import retrofit2.Call

interface RepositoryData {
    suspend fun getMoney() : Call<MoneyResponse>
}