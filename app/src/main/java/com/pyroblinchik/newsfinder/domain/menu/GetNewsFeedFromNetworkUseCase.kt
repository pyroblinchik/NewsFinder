package com.pyroblinchik.newsfinder.domain.menu

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import javax.inject.Inject

class GetNewsFeedFromNetworkUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(filter : FilterNews) = repository.getNewsFeedFromNetwork(filter)
}
