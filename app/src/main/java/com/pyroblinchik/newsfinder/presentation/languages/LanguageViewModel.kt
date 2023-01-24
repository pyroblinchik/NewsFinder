package com.pyroblinchik.newsfinder.presentation.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.newsfinder.domain.base.model.Language
import com.pyroblinchik.newsfinder.domain.languages.GetLanguagesFromDatabaseUseCase
import com.pyroblinchik.newsfinder.util.ExceptionParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val getLanguagesUseCase: GetLanguagesFromDatabaseUseCase
) : ViewModel() {

    private val _languages = MutableStateFlow<List<Language>>(listOf())
    val languages: StateFlow<List<Language>>
        get() = _languages

    private val _uiState = MutableStateFlow<LanguageUIState>(LanguageUIState.Empty)
    val uiState: StateFlow<LanguageUIState>
        get() = _uiState

    init {
        getLanguages()
    }

    private fun getLanguages() {
        _uiState.value = LanguageUIState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val result = getLanguagesUseCase.invoke()
                _languages.value = result
            }
        } catch (error: Exception) {
            error.printStackTrace()
            Timber.d(ExceptionParser.getMessage(error))
            _uiState.value = LanguageUIState.Error(error.message.toString())
            _languages.value = emptyList()
        } finally {
            _uiState.value = LanguageUIState.Loaded
        }
    }
}