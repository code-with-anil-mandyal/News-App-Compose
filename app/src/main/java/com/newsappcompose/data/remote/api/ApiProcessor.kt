package com.newsappcompose.data.remote.api

sealed class ApiProcessor<T> {
    class Loading<T> : ApiProcessor<T>()
    data class Success<T>(val data: T) : ApiProcessor<T>()
    data class Error<T>(val message: String, val code: Int? = null) : ApiProcessor<T>()
    class NetworkError<T> : ApiProcessor<T>()

    companion object {
        fun <T> onLoading(): ApiProcessor<T> = Loading()
        fun <T> onSuccess(data: T): ApiProcessor<T> = Success(data)
        fun <T> onError(message: String, code: Int? = null): ApiProcessor<T> =
            Error(message, code)
        fun <T> onNetwork(isConnected: Boolean): ApiProcessor<T> =
            if (isConnected) onSuccess(Unit as T) else NetworkError()
    }
}