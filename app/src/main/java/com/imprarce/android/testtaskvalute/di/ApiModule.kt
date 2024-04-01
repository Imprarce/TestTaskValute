package com.imprarce.android.testtaskvalute.di

import com.imprarce.android.testtaskvalute.data.api.RetrofitApi
import com.imprarce.android.testtaskvalute.utils.Constants.CONNECTION_TIMEOUT
import com.imprarce.android.testtaskvalute.utils.Constants.READ_TIMEOUT
import com.imprarce.android.testtaskvalute.utils.Constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Singleton
    @Provides
    fun provideRetrofitApi(): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }
    @Singleton
    @Provides
    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }
}