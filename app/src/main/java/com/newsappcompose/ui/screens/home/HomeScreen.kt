package com.newsappcompose.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.newsappcompose.R
import com.newsappcompose.data.remote.api.ApiProcessor
import com.newsappcompose.domain.models.TopNewsResponse
import com.newsappcompose.ui.common.CustomAppBar
import com.newsappcompose.ui.common.NewsItemView
import com.newsappcompose.ui.model.TopHeadlinesUiModel
import com.newsappcompose.ui.theme.AppBlackColor

@Composable
fun HomeScreen(onItemClick : (source : String, sourceUrl : String) -> Unit){
    val homeViewModel: HomeViewModel = hiltViewModel()
    val newsState = homeViewModel.headlines.collectAsState()

    Column() {
        CustomAppBar(onBackClick = {
        }, title = stringResource(
            R.string.top_news)
        )

        when (val result = newsState.value) {
            is ApiProcessor.Success -> {
                // No need for null check now
                NewsListView(result.data, onItemClick ={ source, sourceUrl->
                    onItemClick(source, sourceUrl)
                })
            }
            is ApiProcessor.Error -> {
                Text("Error: ${result.message}")
            }
            is ApiProcessor.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is ApiProcessor.NetworkError -> {
                Text(text = stringResource(R.string.no_internet_connection))
            }
        }
    }

}

@Composable
fun NewsListView(newsList : List<TopHeadlinesUiModel>, onItemClick: (source : String, url : String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(color = AppBlackColor)
    ) {
        items(newsList){ data ->
            NewsItemView(data, onItemClick = { source, sourceUrl->
                onItemClick(source, sourceUrl)
            })
        }
    }
}