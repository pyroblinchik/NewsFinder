package com.pyroblinchik.newsfinder.data.database.dao

import androidx.room.*
import com.pyroblinchik.newsfinder.data.database.model.NewsDBModel

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(obj: NewsDBModel)

    @Transaction
    @Query("SELECT n.* FROM news n where id = :id")
    fun getNewsById(id:Int) : NewsDBModel?

    @Transaction
    @Query("SELECT n.* FROM news n where isInHistory = 1")
    fun getHistoryNews() : List<NewsDBModel>?

    @Transaction
    @Query("SELECT n.* FROM news n where isFavorite = 1")
    fun getFavouritesNews() : List<NewsDBModel>?


    @Query("DELETE FROM news")
    fun deleteAll()

}