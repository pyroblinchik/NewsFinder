package com.pyroblinchik.newsfinder.data.mapper

import com.pyroblinchik.newsfinder.data.database.model.LanguageDBModel
import com.pyroblinchik.newsfinder.domain.base.model.Language
import javax.inject.Inject

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






