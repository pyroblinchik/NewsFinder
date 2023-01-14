package com.pyroblinchik.newsfinder.data.mapper

import com.pyroblinchik.newsfinder.data.database.model.LanguageDBModel
import com.pyroblinchik.newsfinder.data.database.model.NewsDBModel
import com.pyroblinchik.newsfinder.data.network.model.NewsResponceDto
import com.pyroblinchik.newsfinder.domain.base.model.Language
import com.pyroblinchik.newsfinder.domain.base.model.News
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class LanguageMapper @Inject constructor() {

    fun mapDbModelToEntity(dbModel: LanguageDBModel) = Language(
        dbModel.id,
        dbModel.name,
        dbModel.nameEng,
        dbModel.code
    )

    fun mapEntityToDBModel(language: Language) = LanguageDBModel(
        language.id,
        language.name,
        language.nameEng,
        language.code
    )

}






