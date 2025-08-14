package com.newsappcompose.data.repository

import com.newsappcompose.data.local.preferences.AppPreferences
import com.newsappcompose.data.remote.api.ApiProcessor
import com.newsappcompose.data.remote.api.ApiService
import com.newsappcompose.helpers.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    private val apiService: ApiService,
    private val storage: AppPreferences,
    private val networkHelper: NetworkHelper
)  {

    fun <T : Any> makeCall(
        request: suspend ApiService.() -> Response<T>,
        showLoader: Boolean
    ): Flow<ApiProcessor<T>> = flow {
        if (!networkHelper.isNetworkAvailable()) {
            emit(ApiProcessor.onNetwork<T>(false))
            return@flow
        }

        if (showLoader) {
            emit(ApiProcessor.onLoading<T>())
        }

        val response = request(apiService)

        when {
            response.isSuccessful && response.body() != null -> {
                emit(ApiProcessor.onSuccess(response.body()!!))
            }
            response.isSuccessful && response.body() == null -> {
                emit(ApiProcessor.onError<T>("Empty Response", code = response.code()))
            }
            response.code() == 401 -> {
                storage.clear()
                emit(ApiProcessor.onError<T>("Unauthorized", 401))
            }
            response.code() == 404 -> {
                emit(ApiProcessor.onError<T>("Not Found", 404))
            }
            response.code() == 500 -> {
                emit(ApiProcessor.onError<T>("Internal Server Error", 500))
            }
            else -> {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                emit(ApiProcessor.onError<T>(errorBody, response.code()))
            }
        }
    }.catch { e ->
        emit(ApiProcessor.onError<T>(e.localizedMessage ?: "Unexpected error"))
    }.flowOn(Dispatchers.IO)
}