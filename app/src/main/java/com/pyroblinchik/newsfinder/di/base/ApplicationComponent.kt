package com.pyroblinchik.newsfinder.di.base

import android.app.Application
import com.pyroblinchik.newsfinder.MainActivity
import com.pyroblinchik.newsfinder.SKApplication
import com.pyroblinchik.newsfinder.di.DataModule
import com.pyroblinchik.newsfinder.presentation.menu.MenuActivity
import com.pyroblinchik.newsfinder.presentation.menu.fragments.HistoryFragment
import com.pyroblinchik.newsfinder.presentation.menu.fragments.HomeFragment
import com.pyroblinchik.newsfinder.presentation.menu.fragments.ProfileFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: HomeFragment)

    fun inject(activity: MenuActivity)

    fun inject(activity: ProfileFragment)

    fun inject(activity: HistoryFragment)

    fun inject(application: SKApplication)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
