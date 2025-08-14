package com.newsappcompose.domain.repository

import com.newsappcompose.data.remote.api.ApiProcessor
import com.newsappcompose.domain.models.TopNewsResponse
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesRepository {
    fun getTopHeadlines(
        country: String,
        category: String,
        apiKey: String,
        showLoader: Boolean = false
    ): Flow<ApiProcessor<TopNewsResponse>>
}