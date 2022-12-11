package com.pyroblinchik.newsfinder

import android.app.Application
import com.pyroblinchik.newsfinder.di.base.DaggerApplicationComponent
import timber.log.Timber

class SKApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        initLogger()
        super.onCreate()
    }


    private fun initLogger() = Timber.plant(Timber.DebugTree())
    //   override fun getWorkManagerConfiguration(): Configuration {
    //       return Configuration.Builder()
    //           .setWorkerFactory(workerFactory)
    //           .build()
    //   }
}
