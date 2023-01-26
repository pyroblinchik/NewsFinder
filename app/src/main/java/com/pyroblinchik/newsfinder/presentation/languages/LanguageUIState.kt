package com.pyroblinchik.newsfinder.presentation.languages

sealed class LanguageUIState {
    object Empty : LanguageUIState()
    object Loading : LanguageUIState()
    object Loaded : LanguageUIState()
    object Finish : LanguageUIState()

    class Error(val message: String) : LanguageUIState()
}