package com.newsappcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.newsappcompose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout : () -> Unit){
    LaunchedEffect (true){
        delay(1500)
        onTimeout()
    }
    Box(modifier = Modifier.fillMaxSize()
        .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.top_news),
            style = TextStyle(
                fontSize = 50.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.boldena_bold))
            )
        )
    }
}