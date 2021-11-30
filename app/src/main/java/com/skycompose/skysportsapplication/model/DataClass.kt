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
    var headline: StoryTitle,
    var media: List<StoryMedia>,
    var imageUrl: String?
)

data class StoryMedia(
    val links: StoryMediaLinks
)

data class StoryMediaLinks(
    val fileReference: String
)

data class StoryTitle(
    val mobile: String
)

data class StoryType(
    val description: String
)

val heroStory = Story(
    id = 12344571,
    type = StoryType("Video"),
    headline = StoryTitle("Should MMA be an Olympic sport?"),
    media = listOf(
        StoryMedia(
            StoryMediaLinks("https://e0.365dm.com/21/06/768x432/skysports-mma-cage_5431086.jpg?20210629101243")
        )
    ),
    imageUrl = null
)

val newsStory = Story(
    id = 12346348,
    type = StoryType("News Story"),
    headline = StoryTitle("Tour de France withdraws lawsuit against spectator over crash"),
    media = listOf(
        StoryMedia(
            StoryMediaLinks("https://e0.365dm.com/21/06/768x432/skysports-tour-crash-tour-de-france_5429585.png?20210627202215")

        )
    ),
    imageUrl = null
)