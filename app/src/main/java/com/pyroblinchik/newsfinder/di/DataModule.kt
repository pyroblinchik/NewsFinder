package com.pyroblinchik.newsfinder.di

import android.app.Application
import com.pyroblinchik.newsfinder.data.repository.MenuRepositoryImpl
import com.pyroblinchik.newsfinder.di.base.ApplicationScope
import com.pyroblinchik.newsfinder.domain.menu.MenuRepository
import dagger.Binds
import dagger.Module


@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMenuRepository(impl: MenuRepositoryImpl): MenuRepository
//
//    companion object {
//
//        @Provides
//        @ApplicationScope
//        fun provideUserDao(
//            application: Application
//        ): UserDao {
//            return AppDatabase.getInstance(application).userDao()
//        }
//
//    }
}
