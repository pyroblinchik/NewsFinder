package com.pyroblinchik.newsfinder.data.repository

import android.app.Application
import com.pyroblinchik.newsfinder.data.database.dao.NewsDao
import com.pyroblinchik.newsfinder.data.mapper.NewsMapper
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.favourites.FavouritesRepository
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val mapper: NewsMapper,
    private val application: Application,
    private val dao: NewsDao
) : FavouritesRepository {

    override suspend fun getFavouritesNewsFromDatabase(): List<News> {
        val listDB = dao.getFavouritesNews()
        val list = ArrayList<News>()
        listDB?.forEach {
            list.add(mapper.mapDbModelToEntity(it))
        }
        return list
    }

    override suspend fun addNewsToFavouritesDatabase(news: News) {
        dao.insertNews(mapper.mapEntityToDBModel(news))
    }
}
