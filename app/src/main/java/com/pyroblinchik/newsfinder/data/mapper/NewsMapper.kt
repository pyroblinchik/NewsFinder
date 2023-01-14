package com.pyroblinchik.newsfinder.data.mapper

import com.pyroblinchik.newsfinder.data.database.model.NewsDBModel
import com.pyroblinchik.newsfinder.data.network.model.NewsResponceDto
import com.pyroblinchik.newsfinder.domain.base.model.News
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class NewsMapper @Inject constructor() {

    fun mapDbModelToEntity(dbModel: NewsDBModel) = News(
        dbModel.id,
        dbModel.author,
        dbModel.title,
        dbModel.description,
        dbModel.url,
        dbModel.source,
        dbModel.image,
        dbModel.category,
        dbModel.language,
        dbModel.country,
        dbModel.published_at,
        dbModel.isFavorite,
        dbModel.isInHistory,
        dbModel.historyGroupId
    )

    fun mapEntityToDBModel(news: News) = NewsDBModel(
        news.id,
        news.author,
        news.title,
        news.description,
        news.url,
        news.source,
        news.image,
        news.category,
        news.language,
        news.country,
        news.published_at,
        news.isFavorite,
        news.isInHistory,
        news.historyGroupId
    )

    fun mapNewsDtoModelToEntity(response: NewsResponceDto): ArrayList<News> {
        val result = ArrayList<News>()
        var fakeID = 0
        response.data?.forEach {
            it.id = fakeID
            result.add(it)
            fakeID++
        }
        return result
    }
}






