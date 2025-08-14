package com.newsappcompose.data.remote.api

import com.newsappcompose.domain.models.TopNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET(TOP_HEADLINES)
    suspend fun topHeadLines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Response<TopNewsResponse>
}