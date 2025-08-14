package com.newsappcompose.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class TopNewsResponse(
    @SerialName("articles")
    val articles: List<Article>?,
    @SerialName("status")
    val status: String?,
    @SerialName("totalResults")
    val totalResults: Int?
) : Parcelable {
    @Parcelize
    data class Article(
        @SerialName("author")
        val author: String?,
        @SerialName("content")
        val content: String?,
        @SerialName("description")
        val description: String?,
        @SerialName("publishedAt")
        val publishedAt: String?,
        @SerialName("source")
        val source: Source?,
        @SerialName("title")
        val title: String?,
        @SerialName("url")
        val url: String?,
        @SerialName("urlToImage")
        val urlToImage: String?
    ) : Parcelable {
        @Parcelize
        data class Source(
            @SerialName("id")
            val id: String?,
            @SerialName("name")
            val name: String?
        ) : Parcelable
    }
}