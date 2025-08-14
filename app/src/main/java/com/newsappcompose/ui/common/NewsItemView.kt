package com.newsappcompose.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.newsappcompose.R
import com.newsappcompose.ui.model.TopHeadlinesUiModel
import com.newsappcompose.ui.theme.TextGrayColor

@Composable
fun NewsItemView(data : TopHeadlinesUiModel, onItemClick : (source : String, url : String) -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth()
            .clickable{
               onItemClick(data.source, data.sourceUrl)
            }
            .padding(vertical = 16.dp)
    ) {
        AsyncImage(
            model =data.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .height(200.dp), // you can adjust height as needed
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = data.title?:"",
            style = TextStyle(fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                color = Color.White)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = data.description?:"",
            style = TextStyle(fontWeight = FontWeight(500),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                color = TextGrayColor)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = data.publishedDate?:"",
            style = TextStyle(color = Color.Gray,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))

    }
}