package com.pyroblinchik.newsfinder.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pyroblinchik.newsfinder.data.database.dao.*
import com.pyroblinchik.newsfinder.data.database.model.*

@Database(
    entities = [
        NewsDBModel::class,
        LanguageDBModel::class
    ],
    version = 1
)
@TypeConverters(OfflineConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun languageDao(): LanguagesDao

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
//                        .fallbackToDestructiveMigrationOnDowngrade()
                        .build()
                db = instance
                return instance
            }
        }
    }
}



