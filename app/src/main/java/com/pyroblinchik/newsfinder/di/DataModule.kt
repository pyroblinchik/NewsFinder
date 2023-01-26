package com.pyroblinchik.newsfinder.di

import android.app.Application
import com.pyroblinchik.newsfinder.data.database.AppDatabase
import com.pyroblinchik.newsfinder.data.database.dao.LanguagesDao
import com.pyroblinchik.newsfinder.data.database.dao.NewsDao
import com.pyroblinchik.newsfinder.data.repository.FavouritesRepositoryImpl
import com.pyroblinchik.newsfinder.data.repository.LanguagesRepositoryImpl
import com.pyroblinchik.newsfinder.data.repository.MenuRepositoryImpl
//import com.pyroblinchik.newsfinder.di.base.ApplicationScope
import com.pyroblinchik.newsfinder.domain.favourites.FavouritesRepository
import com.pyroblinchik.newsfinder.domain.languages.LanguagesRepository
import com.pyroblinchik.newsfinder.domain.menu.MenuRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    // jetpack compose is simply better than xml, u just gotta admit it
    @Binds
    @Singleton
    fun bindMenuRepository(impl: MenuRepositoryImpl): MenuRepository

    @Binds
    @Singleton
    fun bindFavouritesRepository(impl: FavouritesRepositoryImpl): FavouritesRepository

    @Binds
    @Singleton
    fun bindLanguagesRepository(impl: LanguagesRepositoryImpl): LanguagesRepository

    companion object {

        @Provides
        fun provideNewsDao(
            application: Application
        ): NewsDao {
            return AppDatabase.getInstance(application).newsDao()
        }

        @Provides
        fun provideLanguageDao(
            application: Application
        ): LanguagesDao {
            return AppDatabase.getInstance(application).languageDao()
        }
    }
}
