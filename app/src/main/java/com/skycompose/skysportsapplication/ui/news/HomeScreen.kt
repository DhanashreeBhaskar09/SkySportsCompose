package com.skycompose.skysportsapplication.ui.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skycompose.skysportsapplication.model.Story
import com.skycompose.skysportsapplication.ui.components.AppTopBar

@Composable
fun HomeScreen(
    homeViewModel: HomeScreenViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    HomeScreen(
        uiState = uiState,
        onRefreshPosts = { homeViewModel.fetchNewsData() },
        scaffoldState = scaffoldState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(uiState: HomeUiState, onRefreshPosts: () -> Unit, scaffoldState: ScaffoldState) {
    val scrollState = rememberLazyListState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppTopBar() }
    ) { innerPadding ->
        val modifier = Modifier
            .padding(innerPadding)

        LoadingContent(
            empty = uiState.initialLoad,
            emptyContent = { FullScreenLoading() },
            loading = uiState.loading,
            onRefresh = onRefreshPosts,
            content = {
                HomeScreen(
                    stories = uiState.stories,
                    isShowingErrors = uiState.errorMessages.isNotEmpty(),
                    onRefresh = { onRefreshPosts() },
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .widthIn(max = 840.dp),
                    scrollState = scrollState
                )
            })
    }
}

@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(loading),
            onRefresh = onRefresh,
            content = content,
        )
    }
}

@Composable
private fun HomeScreen(
    stories: List<Story>,
    isShowingErrors: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState
) {
    if (stories.isNotEmpty()) {
        StoryList(stories, modifier, scrollState)
    } else {
        Box(modifier.fillMaxSize()) {}
    }
}

@Composable
private fun StoryList(
    stories: List<Story>,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
) {
    val heroStory = stories[0]
    val story: List<Story> = stories.subList(1, stories.size)

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .padding(top = 4.dp),
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { HeroStoryCard(heroStory) }

        items(story) { story ->
            StoryCard(story = story)
        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}