package com.pyroblinchik.newsfinder.data.repository

import android.app.Application
import com.pyroblinchik.newsfinder.data.mapper.NewsMapper
import com.pyroblinchik.newsfinder.data.network.factory.NewsApiFactory
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.menu.MenuRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val mapper: NewsMapper,
    private val application: Application
) : MenuRepository {

    override suspend fun getNewsFeed(
    ): List<News> {
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


}
