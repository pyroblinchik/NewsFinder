package com.pyroblinchik.newsfinder.data.repository

import android.app.Application
import com.pyroblinchik.newsfinder.data.database.dao.LanguagesDao
import com.pyroblinchik.newsfinder.data.mapper.LanguageMapper
import com.pyroblinchik.newsfinder.domain.base.model.Language
import com.pyroblinchik.newsfinder.domain.languages.LanguagesRepository
import javax.inject.Inject

class LanguagesRepositoryImpl @Inject constructor(
    private val mapper: LanguageMapper,
    private val application: Application,
    private val dao: LanguagesDao
) : LanguagesRepository {

    override suspend fun getLanguagesFromDatabase(): List<Language> {
        val listDB = dao.getLanguages()
        val list = ArrayList<Language>()
        listDB?.forEach {
            list.add(mapper.mapDbModelToEntity(it))
        }
        return list
    }
}
