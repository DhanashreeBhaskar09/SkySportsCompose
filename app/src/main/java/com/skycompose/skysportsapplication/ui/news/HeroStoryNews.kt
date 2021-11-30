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

@Composable
fun RenderHeroStory(story: Story, modifier: Modifier = Modifier) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        Image(
            /*painter = rememberImagePainter(story.imageUrl),*/
            painterResource(id = R.drawable.placeholder),
            contentDescription = story.headline.mobile,
            contentScale = ContentScale.FillBounds,
            modifier = modifier.fillMaxSize()
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(5.dp),
                text = story.headline.mobile,
                style = MaterialTheme.typography.h5,
                maxLines = 3,
            )
        }
    }
}

@Preview
@Composable
fun PreviewHeroStory() {
    RenderHeroStory(story = heroStory)
}