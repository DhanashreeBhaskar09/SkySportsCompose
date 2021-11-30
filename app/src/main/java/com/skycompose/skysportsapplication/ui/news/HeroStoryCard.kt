package com.skycompose.skysportsapplication.ui.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import com.skycompose.skysportsapplication.model.heroStory

@Composable
fun HeroStoryCard(story: Story, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        Box {
            Image(
                painter = rememberImagePainter(story.imageUrl),
                //painterResource(id = R.drawable.placeholder),
                contentDescription = story.headline.mobile,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .fillMaxSize()
            )

            Text(
                modifier = modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .fillMaxWidth()
                    .background(color = Color.Yellow)
                    .padding(8.dp),
                text = story.headline.mobile,
                style = MaterialTheme.typography.h6,
                maxLines = 3,
            )
        }
    }
}

@Preview
@Composable
fun PreviewHeroStory() {
    HeroStoryCard(story = heroStory)
}