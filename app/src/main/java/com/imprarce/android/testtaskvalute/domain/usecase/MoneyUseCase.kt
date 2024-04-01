package com.imprarce.android.testtaskvalute.domain.usecase

import com.imprarce.android.testtaskvalute.domain.repository.DomainRepository

class MoneyUseCase(private val domainRepository: DomainRepository) {
    suspend fun callMoneyApi() = domainRepository.fetchMoneyList()
}