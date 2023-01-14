package com.pyroblinchik.newsfinder.domain.languages

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import javax.inject.Inject

class GetLanguagesFromDatabaseUseCase @Inject constructor(
    private val repository: LanguagesRepository
) {

    suspend operator fun invoke() = repository.getLanguagesFromDatabase()
}
