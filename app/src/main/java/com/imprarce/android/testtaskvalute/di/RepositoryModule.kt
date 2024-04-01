package com.imprarce.android.testtaskvalute.di

import com.imprarce.android.testtaskvalute.data.api.RetrofitApi
import com.imprarce.android.testtaskvalute.data.repository.RepositoryData
import com.imprarce.android.testtaskvalute.data.repositoryimpl.RepositoryDataImpl
import com.imprarce.android.testtaskvalute.domain.repository.DomainRepository
import com.imprarce.android.testtaskvalute.domain.repositoryimpl.DomainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesRepositoryData(api: RetrofitApi): RepositoryData {
        return RepositoryDataImpl(api)
    }

    @Singleton
    @Provides
    fun provideDomainRepository(repositoryData: RepositoryData): DomainRepository {
        return DomainRepositoryImpl(repositoryData)
    }
}