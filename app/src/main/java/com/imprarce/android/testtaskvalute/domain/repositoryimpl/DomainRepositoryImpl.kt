package com.imprarce.android.testtaskvalute.domain.repositoryimpl

import com.imprarce.android.testtaskvalute.data.api.Fetch
import com.imprarce.android.testtaskvalute.data.api.response.MoneyResponse
import com.imprarce.android.testtaskvalute.data.repository.RepositoryData
import com.imprarce.android.testtaskvalute.domain.repository.DomainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class DomainRepositoryImpl(private val repositoryData: RepositoryData) : Fetch(), DomainRepository {
    override suspend fun fetchMoneyList(): Call<MoneyResponse> {
        return withContext(Dispatchers.IO){
            repositoryData.getMoney()
        }
    }
}