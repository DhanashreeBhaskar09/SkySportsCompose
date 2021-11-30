package com.skycompose.skysportsapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skycompose.skysportsapplication.R

@Composable
fun AppTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Column(
            modifier = Modifier
                .width(180.dp)
                .fillMaxHeight()
                .background(Color.Blue, shape = RectangleShape)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(id = R.drawable.ic_sky_logo),
                    contentDescription = "Sky",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 2.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight()
                .background(Color.Red, shape = RectangleShape)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(id = R.drawable.ic_sports_logo),
                    contentDescription = "Sports logo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 2.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAppTopBar() {
    AppTopBar()
}