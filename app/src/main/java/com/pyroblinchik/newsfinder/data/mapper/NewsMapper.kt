package com.pyroblinchik.newsfinder.data.mapper

import com.pyroblinchik.newsfinder.data.database.model.NewsDBModel
import com.pyroblinchik.newsfinder.data.network.model.NewsResponseDto
import com.pyroblinchik.newsfinder.domain.base.model.News
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
        dbModel.historyGroupId,
        dbModel.historyDate
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
        news.historyGroupId,
        news.historyDate
    )


    fun mapNewsDtoModelToEntity(response: NewsResponseDto): ArrayList<News> {
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






