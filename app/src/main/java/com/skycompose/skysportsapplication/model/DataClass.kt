package com.skycompose.skysportsapplication.model

data class NewsModule(
    var modules: List<News>
)

data class News(
    var type: String,
    var data: NewsData
)

data class NewsData(
    val items: List<Story>
)

data class Story(
    val id: Int,
    val type: StoryType,
    var headline: StoryTitle
)

data class StoryTitle(
    val mobile: String
)

data class StoryType(
    val description: String
)