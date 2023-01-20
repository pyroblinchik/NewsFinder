package com.pyroblinchik.newsfinder.domain.base.model

import com.pyroblinchik.newsfinder.data.Constants.ENGLISH_LANGUAGE_CODE
import com.pyroblinchik.newsfinder.data.Constants.SYSTEM_THEME_CODE


data class UserSettings(
    var theme : Int = SYSTEM_THEME_CODE,
    var language : Int = ENGLISH_LANGUAGE_CODE
)