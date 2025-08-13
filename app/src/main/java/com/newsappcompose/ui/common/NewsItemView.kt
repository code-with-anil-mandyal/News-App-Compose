package com.newsappcompose.ui.common

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.newsappcompose.R
import com.newsappcompose.ui.theme.TextGrayColor

@Composable
fun NewsItemView(){
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        AsyncImage(
            model = "https://www.newsherald.com/gcdn/authoring/authoring-images/2025/07/29/NTNH/85425989007-250729-tobeach-003.JPG?crop=2199,1238,x0,y166&width=2199&height=1238&format=pjpg&auto=webp",
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Flesh-eating' bacteria claims second Bay County victim in 2025; fifth across Florida - Panama City News Herald",
            style = TextStyle(fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                color = Color.White)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "A 78-year-old man died at a Bay hospital on Aug. 4 after contracting vibrio vulnificus. This marks the second local vibrio vulnificus death in 2025.",
            style = TextStyle(fontWeight = FontWeight(500),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular)),
                color = TextGrayColor)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "2025-08-13T18:41:53Z",
            style = TextStyle(color = Color.Gray,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))

    }
}