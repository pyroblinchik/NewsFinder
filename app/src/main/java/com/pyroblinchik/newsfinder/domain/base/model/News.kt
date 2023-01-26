package com.pyroblinchik.newsfinder.domain.base.model


data class News(
    var id: Int = 0,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var source: String? = null,
    var image: String? = null,
    var category: String? = null,
    var language: String? = null,
    var country: String? = null,
    var published_at: String? = null,
    var isFavorite: Boolean = false,
    var isInHistory: Boolean = false,
    var historyGroupId: Int = 0,
    var historyDate: String? = null
)