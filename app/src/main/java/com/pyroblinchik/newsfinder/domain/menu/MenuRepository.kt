package com.pyroblinchik.newsfinder.domain.menu

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.base.model.UserSettings

interface MenuRepository {

    suspend fun getNewsFeedFromNetwork(filter : FilterNews): List<News>

    suspend fun getNewsHistoryFromDatabase(): List<News>

    suspend fun getUserSettingsFromDatabase(): UserSettings

    suspend fun updateUserSettingsFromDatabase(settings: UserSettings): UserSettings

    suspend fun addNewsToHistoryDatabase(news: News)
}
