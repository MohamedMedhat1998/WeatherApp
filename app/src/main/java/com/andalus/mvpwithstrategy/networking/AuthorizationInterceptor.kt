package com.andalus.mvpwithstrategy.networking

import com.andalus.mvpwithstrategy.utils.Constants
import com.andalus.mvpwithstrategy.utils.Secrets.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrl = request
            .url
            .newBuilder()
            .addEncodedQueryParameter(Constants.APP_ID, API_KEY)
            .build()
        val newRequest = chain.request().newBuilder().url(httpUrl).build()
        return chain.proceed(newRequest)
    }
}