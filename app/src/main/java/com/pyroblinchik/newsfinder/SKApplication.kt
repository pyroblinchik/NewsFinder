package com.pyroblinchik.newsfinder

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SKApplication : Application() {


    override fun onCreate() {
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
