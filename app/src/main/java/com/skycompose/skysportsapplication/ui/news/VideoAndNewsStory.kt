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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.skycompose.skysportsapplication.R
import com.skycompose.skysportsapplication.model.Story
import com.skycompose.skysportsapplication.model.heroStory
import com.skycompose.skysportsapplication.model.newsStory

@Composable
fun RenderStory(story: Story) {
    if (story.imageUrl != null) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.height(100.dp)
        ) {
            Row(modifier = Modifier.background(Color.LightGray)) {
                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(110.dp)
                ) {
                    Image(
                        //painter = rememberImagePainter(story.imageUrl),
                        painterResource(id = R.drawable.placeholder),
                        contentDescription = "Video image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(Color.LightGray),
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
            modifier = Modifier
                .height(90.dp)
        ) {
            Column(modifier = Modifier.background(Color.LightGray)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(Color.LightGray),
                        text = story.headline.mobile,
                        style = MaterialTheme.typography.h6,
                        maxLines = 3,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewStory() {
    //RenderStory(story = heroStory)
    RenderStory(story = newsStory)
}

