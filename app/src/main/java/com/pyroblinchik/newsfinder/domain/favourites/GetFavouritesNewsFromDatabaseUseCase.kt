package com.pyroblinchik.newsfinder.domain.favourites

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import javax.inject.Inject

class GetFavouritesNewsFromDatabaseUseCase @Inject constructor(
    private val repository: FavouritesRepository
) {

    suspend operator fun invoke() = repository.getFavouritesNewsFromDatabase()
}
