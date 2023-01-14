package com.pyroblinchik.newsfinder.data.database.dao

import androidx.room.*
import com.pyroblinchik.newsfinder.data.database.model.LanguageDBModel

@Dao
interface LanguagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguage(obj: LanguageDBModel)

    @Transaction
    @Query("SELECT l.* FROM language l")
    fun getLanguages() : List<LanguageDBModel>?

    @Query("DELETE FROM news")
    fun deleteAll()

}