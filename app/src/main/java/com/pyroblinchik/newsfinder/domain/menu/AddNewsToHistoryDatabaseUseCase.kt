package com.pyroblinchik.newsfinder.domain.menu

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import com.pyroblinchik.newsfinder.domain.base.model.News
import javax.inject.Inject

class AddNewsToHistoryDatabaseUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(news: News) = repository.addNewsToHistoryDatabase(news)
}
