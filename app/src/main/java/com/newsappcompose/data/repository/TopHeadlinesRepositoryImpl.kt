package com.newsappcompose.data.repository

import com.newsappcompose.data.local.preferences.AppPreferences
import com.newsappcompose.data.remote.api.ApiProcessor
import com.newsappcompose.data.remote.api.ApiService
import com.newsappcompose.domain.models.TopNewsResponse
import com.newsappcompose.domain.repository.TopHeadlinesRepository
import com.newsappcompose.helpers.NetworkHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val storage: AppPreferences,
    private val networkHelper: NetworkHelper
) : BaseRepository(apiService, storage, networkHelper), TopHeadlinesRepository {

    override fun getTopHeadlines(
        country: String,
        category: String,
        apiKey: String,
        showLoader: Boolean
    ): Flow<ApiProcessor<TopNewsResponse>> {
        return makeCall(
            request = { topHeadLines(country, category, apiKey) },
            showLoader = showLoader
        )
    }
}