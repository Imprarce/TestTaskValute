package com.imprarce.android.testtaskvalute.domain.repositoryimpl

import com.imprarce.android.testtaskvalute.data.api.Fetch
import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.data.repository.RepositoryData
import com.imprarce.android.testtaskvalute.domain.repository.DomainRepository
import com.imprarce.android.testtaskvalute.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DomainRepositoryImpl(private val repositoryData: RepositoryData) : Fetch(), DomainRepository {
    override suspend fun fetchMoneyList(): Flow<ApiResult<MoneyResponse>> {
        return flow {
            val moneyResponse = repositoryData.getMoney()
            emit(fetchMoney { moneyResponse })
        }
    }
}