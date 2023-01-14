package com.pyroblinchik.newsfinder.domain.languages


import com.pyroblinchik.newsfinder.domain.base.model.Language

interface LanguagesRepository {
    suspend fun getLanguagesFromDatabase(): List<Language>
}
