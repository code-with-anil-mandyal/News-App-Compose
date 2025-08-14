package com.newsappcompose.ui.screens.newsDetails

import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.newsappcompose.ui.common.CustomAppBar

@Composable
fun NewsDetailsScreen(source : String, sourceUrl : String, onBackClick : () -> Unit){

    Column() {
        CustomAppBar(
            isBackButton = true,
            onBackClick = {
                onBackClick()
            }, title = source

        )

        LoadWebNews(sourceUrl,
            modifier = Modifier.weight(1f))
    }
}

@Composable
fun LoadWebNews(webSource: String, modifier: Modifier= Modifier) {
    var progress by remember { mutableStateOf(0) }
    var isLoading by remember { mutableStateOf(true) }

    Column(modifier = modifier.fillMaxSize()) {

        // Progress bar at the top
        /*if (isLoading) {
            LinearProgressIndicator(
                progress = progress / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }*/

        // WebView
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            isLoading = true
                            super.onPageStarted(view, url, favicon)
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading = false

                            // Inject JavaScript to hide ads
                            val jsCode = """
            javascript:(function() {
                var ads = document.querySelectorAll('.ad-container, .banner, [id*="ad"], [class*="ad"]');
                for (var i = 0; i < ads.length; i++) {
                    ads[i].style.display = 'none';
                }
            })();
            """

                            view?.evaluateJavascript(jsCode, null)
                            super.onPageFinished(view, url)

                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            view?.loadUrl(request?.url.toString())
                            return true
                        }
                    }

                    webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            progress = newProgress
                            isLoading = newProgress < 100
                            super.onProgressChanged(view, newProgress)
                        }
                    }

                    loadUrl(webSource)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}