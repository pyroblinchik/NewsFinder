package com.pyroblinchik.newsfinder.domain.menu

import com.pyroblinchik.newsfinder.domain.base.model.News

interface MenuRepository {

    suspend fun getNewsFeed(): List<News>

//    suspend fun getNewsHistory(): List<News>
}
