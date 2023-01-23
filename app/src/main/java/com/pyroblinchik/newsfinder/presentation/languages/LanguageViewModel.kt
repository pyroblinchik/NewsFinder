package com.pyroblinchik.newsfinder.presentation.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.newsfinder.domain.base.model.Language
import com.pyroblinchik.newsfinder.domain.languages.GetLanguagesFromDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val getLanguagesUseCase: GetLanguagesFromDatabaseUseCase
) : ViewModel() {

    private val _languages = MutableStateFlow<List<Language>>(listOf())
    val languages: StateFlow<List<Language>>
        get() = _languages

    init {
        getLanguages()
    }

    private fun getLanguages() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getLanguagesUseCase.invoke()
            _languages.value = result
        }
    }
}