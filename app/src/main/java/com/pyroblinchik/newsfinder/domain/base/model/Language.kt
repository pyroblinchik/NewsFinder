package com.pyroblinchik.newsfinder.domain.base.model

import com.pyroblinchik.newsfinder.data.Constants.ENGLISH_LANGUAGE_CODE
import com.pyroblinchik.newsfinder.data.Constants.SYSTEM_THEME_CODE


class Language{
    constructor()
    constructor(id: Int, name: String?, nameEng: String?, code: String?) {
        this.id = id
        this.name = name
        this.nameEng = nameEng
        this.code = code
    }

    var id: Int = 0
    var name : String? = null
    var nameEng: String? = null
    var code: String? = null
}
