package com.skycompose.skysportsapplication.ui.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skycompose.skysportsapplication.R
import com.skycompose.skysportsapplication.model.Story

@Composable
fun RenderStory(story: Story) {
    if (story.type.description == "Video") {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.height(100.dp).padding(start = 8.dp, top = 2.dp, end = 8.dp, bottom = 2.dp)) {
            Row(modifier = Modifier.background(Color.LightGray))  {
                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(110.dp).padding(8.dp).background(Color.Transparent)) {
                    Image(
                        painterResource(id = R.drawable.placeholder),
                        contentDescription = "Video image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize())
                }
                Box(modifier = Modifier.fillMaxSize().padding(4.dp)) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart).fillMaxWidth(),
                        text = story.headline.mobile,
                        style = MaterialTheme.typography.h6,
                        maxLines = 3,
                    )
                }
            }
        }
    } else {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.height(90.dp).padding(start = 8.dp, top = 2.dp, end = 8.dp, bottom = 2.dp)) {
            Column(modifier = Modifier.background(Color.LightGray)) {
                Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart).fillMaxWidth(),
                        text = story.headline.mobile,
                        style = MaterialTheme.typography.h6,
                        maxLines = 3,
                    )
                }
            }
        }
    }
}

