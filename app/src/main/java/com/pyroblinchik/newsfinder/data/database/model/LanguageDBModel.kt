package com.pyroblinchik.newsfinder.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pyroblinchik.newsfinder.data.Constants.ENGLISH_LANGUAGE_CODE
import com.pyroblinchik.newsfinder.data.Constants.SYSTEM_THEME_CODE

@Entity(
    tableName = "language"
)
data class LanguageDBModel(
    @PrimaryKey
    var id: Int,
    var name : String?,
    var nameEng: String?,
    var code: String?
)
