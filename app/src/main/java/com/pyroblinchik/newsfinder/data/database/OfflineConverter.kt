package com.pyroblinchik.newsfinder.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pyroblinchik.newsfinder.domain.base.model.*

class OfflineConverter {
    @TypeConverter
    fun stringListToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToStringList(value: String?): List<String>? {
        if (value == "null") return null
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun intListToJsonString(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToIntList(value: String?): List<Int>? {
        if (value == "null") return null
        return Gson().fromJson(value, Array<Int>::class.java).toList()
    }


}