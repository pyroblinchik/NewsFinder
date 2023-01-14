package com.pyroblinchik.newsfinder.domain.menu

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import javax.inject.Inject

class GetUserSettingsFromPreferencesUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    suspend operator fun invoke() = repository.getUserSettingsFromDatabase()
}
