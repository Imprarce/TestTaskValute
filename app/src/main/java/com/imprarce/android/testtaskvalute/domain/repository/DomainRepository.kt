package com.imprarce.android.testtaskvalute.domain.repository

import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface DomainRepository {
    suspend fun fetchMoneyList(): Flow<ApiResult<MoneyResponse>>
}