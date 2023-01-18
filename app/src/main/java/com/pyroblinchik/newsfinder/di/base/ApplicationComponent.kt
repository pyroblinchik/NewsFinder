package com.pyroblinchik.newsfinder.di.base

import android.app.Application
import com.pyroblinchik.newsfinder.SKApplication
import com.pyroblinchik.newsfinder.di.DataModule
import com.pyroblinchik.newsfinder.presentation.favourites.FavouritesActivity
import com.pyroblinchik.newsfinder.presentation.languages.LanguageActivity
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivity
import com.pyroblinchik.newsfinder.presentation.menu.fragments.HistoryFragment
import com.pyroblinchik.newsfinder.presentation.menu.fragments.FeedFragment
import com.pyroblinchik.newsfinder.presentation.menu.fragments.ProfileFragment
import com.pyroblinchik.newsfinder.presentation.newsCard.NewsCardActivity
import dagger.BindsInstance
import dagger.Component

/*
// @ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {


    fun inject(fragment: FeedFragment)

    fun inject(activity: MenuActivity)

    fun inject(activity: ProfileFragment)

    fun inject(activity: HistoryFragment)

    fun inject(activity: FavouritesActivity)

    fun inject(activity: LanguageActivity)

    fun inject(activity: NewsCardActivity)

    fun inject(application: SKApplication)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
*/
