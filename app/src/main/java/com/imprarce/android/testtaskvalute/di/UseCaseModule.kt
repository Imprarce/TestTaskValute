package com.imprarce.android.testtaskvalute.di

import com.imprarce.android.testtaskvalute.domain.repository.DomainRepository
import com.imprarce.android.testtaskvalute.domain.usecase.MoneyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideMoneyUseCase(domainRepository: DomainRepository): MoneyUseCase {
        return MoneyUseCase(domainRepository)
    }
}