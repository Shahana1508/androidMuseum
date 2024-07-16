package com.example.practice.di

import com.example.practice.api.ApiKeyInterceptor
import com.example.practice.api.CityService
import com.example.practice.api.Constants.BaseUrl
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
object AppModule {
    @Provides
    @Singleton
    fun provideApiKeyInterceptor():ApiKeyInterceptor{
        return ApiKeyInterceptor()
    }
    @Provides
    @Singleton
    fun provideOkHttp(apiKeyInterceptor: ApiKeyInterceptor):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(apiKeyInterceptor)
            .readTimeout(60,TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    @Singleton
    fun createApi(retrofit:Retrofit):CityService{
        return retrofit.create(CityService::class.java)
    }

}