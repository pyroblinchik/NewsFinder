package com.pyroblinchik.newsfinder.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.menu.GetNewsFeedUseCase
import com.pyroblinchik.newsfinder.util.ExceptionParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MenuActivityViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsFeedUseCase,
) : ViewModel() {

    private val _news = MutableLiveData<ArrayList<News>>()
    val news: LiveData<ArrayList<News>> = _news

    private val _uiState = MutableStateFlow<MenuUIState>(MenuUIState.Empty)
    val uiState: StateFlow<MenuUIState> = _uiState

    private val _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()

    init {
//        viewModelScope.launch {
//            delay(6000)
//            _isLoading.value = false
//        }
        getNews()
    }

    fun updateList() {
        getNews()
    }

    private fun getNews() {
//        _uiState.value = MenuUIState.Loading
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val news = getNewsUseCase.invoke()
//                _uiState.value = if (news.isEmpty()) {
//                    _news.postValue(ArrayList())
//                    MenuUIState.NewsIsEmpty
//                } else {
//                    MenuUIState.Loaded
//                }
//            } catch (error: Exception) {
//                error.printStackTrace()
//                Timber.d(ExceptionParser.getMessage(error).toString())
//                _uiState.value = MenuUIState.Error(ExceptionParser.getMessage(error))
//            }
//        }
    }
}