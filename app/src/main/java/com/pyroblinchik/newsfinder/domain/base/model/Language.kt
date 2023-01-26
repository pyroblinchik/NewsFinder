package com.pyroblinchik.newsfinder.domain.base.model

import com.pyroblinchik.newsfinder.data.Constants.ENGLISH_LANGUAGE_CODE
import com.pyroblinchik.newsfinder.data.Constants.SYSTEM_THEME_CODE


data class Language(
    var id: Int = 0,
    var name: String? = null,
    var nameEng: String? = null,
    var code: String? = null
)