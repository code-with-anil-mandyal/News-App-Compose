package com.newsappcompose.domain.usecase

import com.newsappcompose.data.remote.api.ApiProcessor
import com.newsappcompose.domain.models.TopNewsResponse
import com.newsappcompose.domain.repository.TopHeadlinesRepository
import com.newsappcompose.ui.model.TopHeadlinesUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject


class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: TopHeadlinesRepository
) {
    operator fun invoke(
        country: String,
        category: String,
        apiKey: String,
        showLoader: Boolean
    ): Flow<ApiProcessor<List<TopHeadlinesUiModel>>> {
        return repository.getTopHeadlines(country, category, apiKey, showLoader)
            .map { result ->
                when (result) {
                    is ApiProcessor.Success -> {
                        ApiProcessor.onSuccess(
                            result.data.articles?.map { article ->
                                TopHeadlinesUiModel(
                                    title = article.title ?: "",
                                    description = article.description ?: "",
                                    imageUrl = article.urlToImage ?: "",
                                    publishedDate = formatDate(article.publishedAt),
                                    source = article.source?.name?:"",
                                    sourceUrl = article.url ?:""
                                )
                            } ?: emptyList()
                        )
                    }
                    is ApiProcessor.Error -> {
                        ApiProcessor.onError(
                            message = result.message ?: "Unknown error",
                            code = result.code
                        )
                    }
                    is ApiProcessor.Loading -> {
                        ApiProcessor.onLoading()
                    }
                    is ApiProcessor.NetworkError -> {
                        ApiProcessor.onNetwork(false)
                    }
                }
            }
    }

    private fun formatDate(dateString: String?): String {
        if (dateString.isNullOrBlank()) return ""
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            parser.timeZone = TimeZone.getTimeZone("UTC")
            val date = parser.parse(dateString)
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            formatter.format(date!!)
        } catch (e: Exception) {
            ""
        }
    }
}
