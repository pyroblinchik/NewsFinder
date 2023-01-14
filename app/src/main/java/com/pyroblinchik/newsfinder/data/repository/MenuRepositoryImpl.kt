package com.pyroblinchik.newsfinder.data.repository

import android.app.Application
import com.pyroblinchik.newsfinder.data.database.dao.NewsDao
import com.pyroblinchik.newsfinder.data.mapper.NewsMapper
import com.pyroblinchik.newsfinder.data.network.factory.NewsApiFactory
import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.base.model.UserSettings
import com.pyroblinchik.newsfinder.domain.menu.MenuRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val mapper: NewsMapper,
    private val application: Application,
    private val dao: NewsDao,
) : MenuRepository {

    override suspend fun getNewsFeedFromNetwork(filter: FilterNews): List<News> {
        val result =
            NewsApiFactory().apiService.getNews(
                "",
                "Android",
                "us",
                "general",
                "cnn"
            )

        val mapResult = mapper.mapNewsDtoModelToEntity(result)

        Timber.e(result.toString())

        return mapResult
    }

    override suspend fun getNewsHistoryFromDatabase(): List<News> {
        val listDB = dao.getHistoryNews()
        val list = ArrayList<News>()
        listDB?.forEach {
            list.add(mapper.mapDbModelToEntity(it))
        }
        return list
    }

    override suspend fun addNewsToHistoryDatabase(news: News) {
        dao.insertNews(mapper.mapEntityToDBModel(news))
    }

    override suspend fun getUserSettingsFromDatabase(): UserSettings {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserSettingsFromDatabase(settings: UserSettings): UserSettings {
        TODO("Not yet implemented")
    }


}
