package com.pyroblinchik.newsfinder.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "news"
)
data class NewsDBModel(
    @PrimaryKey
    var id: Int,
    var author : String?,
    var title: String?,
    var description: String?,
    var url : String?,
    var source: String?,
    var image : String?,
    var category: String?,
    var language : String?,
    var country : String?,
    var published_at: String?,
    var isFavorite: Boolean,
    var isInHistory: Boolean,
    var historyGroupId : Int,
    var historyDate: String?
)
