package com.pyroblinchik.newsfinder.domain.favourites

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import com.pyroblinchik.newsfinder.domain.base.model.News

interface FavouritesRepository {
    suspend fun getFavouritesNewsFromDatabase(): List<News>

    suspend fun addNewsToFavouritesDatabase(news: News)

}
