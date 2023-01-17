package com.pyroblinchik.newsfinder.presentation.newsCard

sealed class NewsCardUIState {
    object Empty : NewsCardUIState()
    object Loading : NewsCardUIState()
    object Loaded : NewsCardUIState()
    object Finish: NewsCardUIState()
    class Error(val message: String) : NewsCardUIState()
}