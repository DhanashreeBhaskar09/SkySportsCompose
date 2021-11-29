package com.skycompose.skysportsapplication.ui.news

import androidx.compose.foundation.layout.*
import com.skycompose.skysportsapplication.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skycompose.skysportsapplication.model.Story
import com.skycompose.skysportsapplication.utils.supportWideScreen
import java.util.*


@Composable
fun HomeScreen(
    homeViewModel: HomeScreenViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    HomeScreen(
        uiState = uiState,
        onRefreshPosts = { homeViewModel.fetchNewsData() },
        scaffoldState = scaffoldState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(uiState: HomeUiState, onRefreshPosts: () -> Unit, scaffoldState: ScaffoldState) {
    val scrollState = rememberLazyListState()
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(hostState = it, modifier = Modifier.systemBarsPadding()) },
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        LoadingContent(
            empty = uiState.initialLoad,
            emptyContent = { FullScreenLoading() },
            loading = uiState.loading,
            onRefresh = onRefreshPosts,
            content = {
                HomeScreenErrorAndContent(
                    stories = uiState.stories,
                    isShowingErrors = uiState.errorMessages.isNotEmpty(),
                    onRefresh = { onRefreshPosts() },
                    modifier = modifier.supportWideScreen(),
                    scrollState = scrollState)
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
private fun HomeScreenErrorAndContent(
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
        modifier = modifier,
        state = scrollState,
        contentPadding =
        rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars, applyTop = false)
    ) {
        item { RenderHeroNewsStory(heroStory) }
        item { RenderVideoAndNewsStory(story) }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        CircularProgressIndicator()
    }
}

@Composable
private fun RenderHeroNewsStory(story: Story) {
    Box(modifier = Modifier.fillMaxWidth().height(40.dp).padding(4.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            text = stringResource(id = R.string.home_top_section_title),
            style = MaterialTheme.typography.h5)
    }
    RenderHeroStory(story = story)
}

@Composable
private fun RenderVideoAndNewsStory(stories: List<Story>) {
    Column { stories.forEach { story -> RenderStory(story = story) } }
}
