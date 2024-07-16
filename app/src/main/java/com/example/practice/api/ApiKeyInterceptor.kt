package com.example.practice.api

import com.example.practice.api.Constants.api_Key
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
        val Url=request.url.newBuilder().addQueryParameter("apiKey",api_Key).build()
        val newRequest=request.newBuilder().url(Url).build()
        return chain.proceed(newRequest)
    }
}