package com.newsappcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.newsappcompose.R
import com.newsappcompose.ui.common.CustomAppBar
import com.newsappcompose.ui.common.NewsItemView
import com.newsappcompose.ui.theme.AppBlackColor

@Composable
fun HomeScreen(){

    Column() {
        CustomAppBar(onBackClick = {
        }, title = stringResource(
            R.string.top_news)
        )

        NewsListView()
    }

}

@Composable
fun NewsListView() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(color = AppBlackColor)
    ) {
        items(20){
            NewsItemView()
        }
    }
}