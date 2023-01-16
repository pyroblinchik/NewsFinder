package com.pyroblinchik.newsfinder.di

import android.app.Application
import com.pyroblinchik.newsfinder.data.database.AppDatabase
import com.pyroblinchik.newsfinder.data.database.dao.LanguagesDao
import com.pyroblinchik.newsfinder.data.database.dao.NewsDao
import com.pyroblinchik.newsfinder.data.repository.FavouritesRepositoryImpl
import com.pyroblinchik.newsfinder.data.repository.LanguagesRepositoryImpl
import com.pyroblinchik.newsfinder.data.repository.MenuRepositoryImpl
import com.pyroblinchik.newsfinder.di.base.ApplicationScope
import com.pyroblinchik.newsfinder.domain.favourites.FavouritesRepository
import com.pyroblinchik.newsfinder.domain.languages.LanguagesRepository
import com.pyroblinchik.newsfinder.domain.menu.MenuRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {
    // jetpack compose is simply better than xml, u just gotta admit it
    @Binds
    @ApplicationScope
    fun bindMenuRepository(impl: MenuRepositoryImpl): MenuRepository

    @Binds
    @ApplicationScope
    fun bindFavouritesRepository(impl: FavouritesRepositoryImpl): FavouritesRepository

    @Binds
    @ApplicationScope
    fun bindLanguagesRepository(impl: LanguagesRepositoryImpl): LanguagesRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideNewsDao(
            application: Application
        ): NewsDao {
            return AppDatabase.getInstance(application).newsDao()
        }

        @Provides
        @ApplicationScope
        fun provideLanguageDao(
            application: Application
        ): LanguagesDao {
            return AppDatabase.getInstance(application).languageDao()
        }
    }
}
