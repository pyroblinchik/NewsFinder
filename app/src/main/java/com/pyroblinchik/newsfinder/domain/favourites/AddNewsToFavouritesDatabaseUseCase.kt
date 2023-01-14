package com.pyroblinchik.newsfinder.domain.favourites

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import com.pyroblinchik.newsfinder.domain.base.model.News
import javax.inject.Inject

class AddNewsToFavouritesDatabaseUseCase @Inject constructor(
    private val repository: FavouritesRepository
) {

    suspend operator fun invoke(news: News) = repository.addNewsToFavouritesDatabase(news)
}
