package com.skycompose.skysportsapplication.ui.news

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import com.skycompose.skysportsapplication.model.*
import com.skycompose.skysportsapplication.utils.SkyErrorMessage
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers

data class HomeUiState(
    val stories: List<Story> = emptyList(),
    val favorites: Set<String> = emptySet(),
    val loading: Boolean = false,
    val errorMessages: List<SkyErrorMessage> = emptyList()
) {
    val initialLoad: Boolean
        get() = stories.isEmpty() && favorites.isEmpty() && errorMessages.isEmpty() && loading
}

class HomeScreenViewModel(
    context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(loading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private val applicationContext = context

    init {
        fetchNewsData()
    }

    fun fetchNewsData() {
        _uiState.update { it.copy(loading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            val finalList = mutableListOf<Story>()
            val storyList = mutableListOf<Story>()
            _uiState.update {
                fetchJsonData().modules.iterator().forEach { news ->
                    news.data.items.forEach { story ->
                        val url =
                            "https://e0.365dm.com/" + story.media[0].links.fileReference.replace(
                                "{width}",
                                "768"
                            ).replace("{height}", "432")

                        storyList.add(
                            Story(
                                id = story.id,
                                type = story.type,
                                headline = story.headline,
                                media = story.media,
                                imageUrl = if (url.isNotBlank())
                                    url
                                else
                                    null
                            )
                        )
                    }
                }
                finalList.add(storyList[0])
                finalList.addAll(1, storyList.subList(1, storyList.size).shuffled())
                it.copy(stories = finalList, loading = false)
            }
        }
    }

    fun errorShown(errorId: Long) {
        _uiState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    private fun fetchJsonData(): NewsModule {
        lateinit var jsonString: String
        try {
            jsonString = applicationContext.assets.open("top-stories.json").bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            errorShown(404L)
            ioException.printStackTrace()
        }
        return Gson().fromJson(jsonString, NewsModule::class.java)
    }

    companion object {
        fun provideFactory(
            context: Context
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeScreenViewModel(context) as T
            }
        }
    }
}
