package com.pyroblinchik.newsfinder.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.favourites.GetFavouritesNewsFromDatabaseUseCase
import com.pyroblinchik.newsfinder.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesNewsFromDatabaseUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<FavouritesUIState>(FavouritesUIState.Empty)
    val state: StateFlow<FavouritesUIState>
        get() = _state

    private val _favourites = MutableStateFlow<List<News>>(emptyList())
    val favourites: StateFlow<List<News>>
        get() = _favourites

    init {
        getFavourites()
    }

    private fun getFavourites() {
        _state.value = FavouritesUIState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val result = getFavouritesUseCase.invoke()
                if (result.isEmpty()) {
                    _state.value = FavouritesUIState.FavouritesIsEmpty
                    _favourites.value = emptyList()
                } else {
                    _favourites.value = result
                }
            }
        } catch (error: Exception) {
            _state.value = FavouritesUIState.Error(error.message.toString())
            _favourites.value = emptyList()
            error.printStackTrace()
            Timber.d(ExceptionParser.getMessage(error))
        } finally {
            _state.value = FavouritesUIState.Loaded
        }
    }
}