package com.pyroblinchik.newsfinder.domain.menu

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import javax.inject.Inject

class GetNewsHistoryFromDatabaseUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    suspend operator fun invoke() = repository.getNewsHistoryFromDatabase()

}
