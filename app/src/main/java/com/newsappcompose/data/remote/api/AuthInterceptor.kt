package com.newsappcompose.data.remote.api

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenProvider.getToken() }
        val request = chain.request().newBuilder()
            .addHeader(AUTH_PARAM, "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}