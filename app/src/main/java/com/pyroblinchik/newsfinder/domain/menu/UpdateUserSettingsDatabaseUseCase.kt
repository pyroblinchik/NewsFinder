package com.pyroblinchik.newsfinder.domain.menu

import com.pyroblinchik.newsfinder.domain.base.model.FilterNews
import com.pyroblinchik.newsfinder.domain.base.model.UserSettings
import javax.inject.Inject

class UpdateUserSettingsDatabaseUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(setting: UserSettings) = repository.updateUserSettingsFromDatabase(setting)
}
