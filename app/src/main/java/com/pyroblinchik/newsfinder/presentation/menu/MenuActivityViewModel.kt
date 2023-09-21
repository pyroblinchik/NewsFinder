package com.pyroblinchik.newsfinder.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.menu.AddNewsToHistoryDatabaseUseCase
import com.pyroblinchik.newsfinder.domain.menu.GetNewsFeedFromNetworkUseCase
import com.pyroblinchik.newsfinder.domain.menu.GetNewsHistoryFromDatabaseUseCase
import com.pyroblinchik.newsfinder.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class MenuActivityViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsFeedFromNetworkUseCase,
    private val getNewsHistoryFromDatabaseUseCase: GetNewsHistoryFromDatabaseUseCase,
    private val addNewsToHistoryDatabaseUseCase: AddNewsToHistoryDatabaseUseCase
) : ViewModel() {

    private val _news = MutableLiveData<ArrayList<News>>()
    val news: LiveData<ArrayList<News>>
        get() = _news

    private val _newsHistory = MutableLiveData<ArrayList<News>>()
    val newsHistory: LiveData<ArrayList<News>>
        get() = _newsHistory

    private val _uiState = MutableStateFlow<MenuUIState>(MenuUIState.Empty)
    val uiState: StateFlow<MenuUIState> = _uiState

//    private val _isLoading = MutableStateFlow(true)
//    var isLoading = _isLoading.asStateFlow()

    val _activeTab = MutableLiveData<Int>(0)
    val activeTab: LiveData<Int>
        get() = _activeTab

    private var _searchState = MutableLiveData(false)
    val searchState: LiveData<Boolean>
        get() = _searchState

    init {
        getNews()
        getHistoryNews()


    }

    fun updateNewsList() {
        getNews()
    }

    fun updateHistoryNewsList() {
        getHistoryNews()
    }

    private fun getNews() {
        _uiState.value = MenuUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO "M" Add filter
                val filterNews = FilterNews()

                val news = getNewsUseCase.invoke(filterNews)
                _uiState.value = if (news.isEmpty()) {
                    _news.postValue(ArrayList())
                    MenuUIState.NewsIsEmpty
                } else {
                    _news.postValue(news as ArrayList<News>)
                    MenuUIState.Loaded
                }

            } catch (error: Exception) {
                error.printStackTrace()
                Timber.d(ExceptionParser.getMessage(error))
                _uiState.value = MenuUIState.Error(ExceptionParser.getMessage(error))
            }
        }
    }

    private fun getHistoryNews() {
        _uiState.value = MenuUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val news = getNewsHistoryFromDatabaseUseCase.invoke()
                _uiState.value = if (news.isEmpty()) {
                    _newsHistory.postValue(ArrayList())
                    MenuUIState.HistoryNewsIsEmpty
                } else {
                    _newsHistory.postValue(ArrayList(news))
                    MenuUIState.Loaded
                }

            } catch (error: Exception) {
                error.printStackTrace()
                Timber.d(ExceptionParser.getMessage(error))
                _uiState.value = MenuUIState.Error(ExceptionParser.getMessage(error))
            }
        }
    }

    fun addToHistory(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewsToHistoryDatabaseUseCase.invoke(news)
        }
    }

    private fun getUserSettings() {
        _uiState.value = MenuUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO "I" Get user settings using GetUserSettingsFromPreferencesUseCase

            } catch (error: Exception) {
                error.printStackTrace()
                Timber.d(ExceptionParser.getMessage(error).toString())
                _uiState.value = MenuUIState.Error(ExceptionParser.getMessage(error))
            }
        }
    }

    private fun getLanguages() {
        _uiState.value = MenuUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO "I" Get languages settings using GetLanguagesFromDatabaseUseCase
            } catch (error: Exception) {
                error.printStackTrace()
                Timber.d(ExceptionParser.getMessage(error).toString())
                _uiState.value = MenuUIState.Error(ExceptionParser.getMessage(error))
            }
        }
    }

    fun updateActiveTab(tab: Int) {
        _activeTab.value = tab
    }

    fun updateSearchState(value: Boolean) {
        _searchState.value = value
    }
}