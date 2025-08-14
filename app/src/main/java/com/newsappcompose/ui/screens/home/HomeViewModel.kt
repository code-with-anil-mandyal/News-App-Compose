package com.newsappcompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsappcompose.data.remote.api.APIKEY
import com.newsappcompose.data.remote.api.ApiProcessor
import com.newsappcompose.data.remote.api.CATEGORY
import com.newsappcompose.data.remote.api.COUNTRY
import com.newsappcompose.domain.models.TopNewsResponse
import com.newsappcompose.domain.usecase.GetTopHeadlinesUseCase
import com.newsappcompose.ui.model.TopHeadlinesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {


    init {
        fetchTopHeadlines(COUNTRY, CATEGORY, APIKEY, false)
    }

    private val _headlines = MutableStateFlow(ApiProcessor.onLoading<List<TopHeadlinesUiModel>>())
    val headlines: StateFlow<ApiProcessor<List<TopHeadlinesUiModel>>> = _headlines

    fun fetchTopHeadlines(country: String, category: String, apiKey: String, showLoader: Boolean) {
        viewModelScope.launch {
            getTopHeadlinesUseCase(country, category, apiKey, showLoader)
                .collect { _headlines.value = it }
        }
    }
}
