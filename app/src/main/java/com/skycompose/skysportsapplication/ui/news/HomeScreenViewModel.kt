package com.skycompose.skysportsapplication.ui.news

import android.content.Context
import android.util.Log
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
    val applicationContext = context
    init {
        fetchNewsData()
    }

    fun fetchNewsData() {
        _uiState.update { it.copy(loading = true) }

        Log.d("msg","TEST TEST fetchNewsData");
        viewModelScope.launch {
            val finalList = mutableListOf<Story>()
            val storyList = mutableListOf<Story>()
            _uiState.update {
                fetchJsonData().modules.iterator().forEach { news ->
                    news.data.items.forEach { story ->
                        storyList.add(Story(id = story.id, type = story.type, headline = story.headline))
                    }
                }
                finalList.add(storyList[0])
                finalList.addAll(1, storyList.subList(1, storyList.size).shuffled())
                it.copy(stories = finalList, loading = false)
            }
            Log.d("msg","TEST TEST finalList-${finalList.size}");
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
            jsonString = applicationContext.assets.open("top-stories.json").bufferedReader().use { it.readText() }
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
