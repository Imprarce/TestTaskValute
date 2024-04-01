package com.imprarce.android.testtaskvalute.domain.repository

import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import retrofit2.Call

interface DomainRepository {
    suspend fun fetchMoneyList(): Call<MoneyResponse>
}