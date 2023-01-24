package com.pyroblinchik.newsfinder.presentation.newsCard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.menu.GetNewsFeedFromNetworkUseCase
import com.pyroblinchik.newsfinder.domain.menu.GetNewsHistoryFromDatabaseUseCase
import com.pyroblinchik.newsfinder.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewsCardActivityViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<NewsCardUIState>(NewsCardUIState.Empty)
    val uiState: StateFlow<NewsCardUIState> = _uiState

    init {
        getNews()

    }

    fun updateNewsList() {
        getNews()
    }


    private fun getNews() {
        _uiState.value = NewsCardUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {

            } catch (error: Exception) {
                error.printStackTrace()
                Timber.d(ExceptionParser.getMessage(error))
                _uiState.value = NewsCardUIState.Error(ExceptionParser.getMessage(error))
            }
        }
    }

}