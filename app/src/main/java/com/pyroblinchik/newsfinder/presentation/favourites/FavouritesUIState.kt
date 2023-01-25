package com.pyroblinchik.newsfinder.presentation.favourites

sealed class FavouritesUIState {
    object Empty: FavouritesUIState()
    object Loading: FavouritesUIState()
    object Loaded: FavouritesUIState()
    object Finish: FavouritesUIState()
    object FavouritesIsEmpty: FavouritesUIState()

    class Error(val message: String) : FavouritesUIState()
}
