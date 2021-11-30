package com.skycompose.skysportsapplication.ui.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.skycompose.skysportsapplication.model.Story
import com.skycompose.skysportsapplication.model.newsStory

@Composable
fun StoryCard(story: Story) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(Color.LightGray),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = rememberImagePainter(story.imageUrl),
                //painterResource(id = R.drawable.placeholder),
                contentDescription = "Video image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.LightGray)
                    .padding(top = 4.dp),
                text = story.headline.mobile,
                maxLines = 3,
            )
        }
    }
}


@Preview
@Composable
fun PreviewStory() {
    //RenderStory(story = heroStory)
    StoryCard(story = newsStory)
}

