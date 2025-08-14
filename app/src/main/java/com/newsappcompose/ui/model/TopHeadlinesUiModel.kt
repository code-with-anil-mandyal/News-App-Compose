package com.newsappcompose.ui.model

data class TopHeadlinesUiModel(
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val publishedDate: String,
    val source : String,
    val sourceUrl : String
)