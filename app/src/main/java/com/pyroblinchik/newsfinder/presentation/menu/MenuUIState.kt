package com.pyroblinchik.newsfinder.presentation.menu

sealed class MenuUIState {
    object Empty : MenuUIState()
    object Loading : MenuUIState()
    object Loaded : MenuUIState()
    object Finish: MenuUIState()
    object NewsIsEmpty : MenuUIState()
    class Error(val message: String) : MenuUIState()
}