package com.pyroblinchik.newsfinder.domain.menu

import javax.inject.Inject

class GetNewsFeedUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    suspend operator fun invoke() = repository.getNewsFeed()
}
