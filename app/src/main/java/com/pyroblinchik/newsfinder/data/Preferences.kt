package com.pyroblinchik.newsfinder.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object Preferences {
    const val IP_ADDRESS = "IP_ADDRESS"
    const val CLIENT_ID = "CLIENT_ID"
    const val CLIENT_SECRET = "CLIENT_SECRET"
    const val CHANGE_CONNECT_ANSWER = "CHANGE_CONNECT_ANSWER"
    const val PHOTO_QUALITY = "PHOTO_QUALITY"
    const val OBJECTS_QUANTITY = "OBJECTS_QUANTITY"
    const val DAYS_QUANTITY = "DAYS_QUANTITY"
    const val STORAGE_NAME = "storage_name"
    var sharedPreferences: SharedPreferences? = null
    private fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(STORAGE_NAME, Activity.MODE_PRIVATE)
    }

    private fun putString(key: String?, value: String?) {
        val editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?, defaultValue: String?): String {
        return sharedPreferences!!.getString(key, defaultValue)!!
    }

    private fun putBoolean(key: String?, value: Boolean?) {
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(key, value ?: true)
        editor.apply()
    }

    fun getBoolean(key: String?, defaultValue: Boolean?): Boolean {
        return sharedPreferences!!.getBoolean(key!!, defaultValue ?: true)
    }

    private fun putInt(key: String?, value: Int?) {
        val editor = sharedPreferences!!.edit()
        editor.putInt(key, value ?: 0)
        editor.apply()
    }

    fun getInt(key: String?, defaultValue: Int?): Int {
        return sharedPreferences!!.getInt(key!!, defaultValue ?: 0)
    }

    fun getIP(context: Context): String {
        initialize(context)
     //     return getString(IP_ADDRESS, "http://10.145.1.41:4001/")

      //    return getString(IP_ADDRESS, "https://kgok-chh2.ural.evraz.com:5002/")
        //  return getString(IP_ADDRESS, "http://188.170.57.89:91/")
        return getString(IP_ADDRESS, "https://adeptw.ru/")
    }

    fun setIP(context: Context, string: String) {
        initialize(context)
        putString(IP_ADDRESS, string)
    }

    fun getClientID(context: Context): String {
        initialize(context)
        return getString(CLIENT_ID, "2")
    }

    fun setClientID(context: Context, string: String) {
        initialize(context)
        putString(CLIENT_ID, string)
    }

    fun getClientSecret(context: Context): String {
        initialize(context)
       //  return getString(CLIENT_SECRET, "gsYnFnIzqgNdFJ4P4Ng60qHsHob9SyN24cXvIJKK")
       //    return getString(CLIENT_SECRET, "kxhoLvKW7XebgOYtyWnn6tLTlnBbthNm8pGek2Os")
        return getString(CLIENT_SECRET, "JRq5gAAGAQ8F54qVk9mJFEEmHIhoL1aRAsYgbA15")
    }

    fun setClientSecret(context: Context, string: String) {
        initialize(context)
        putString(CLIENT_SECRET, string)
    }

    fun getConnectAnswer(context: Context): Boolean {
        initialize(context)
        return getBoolean(CHANGE_CONNECT_ANSWER, true)
    }

    fun setConnectAnswer(context: Context, b: Boolean) {
        initialize(context)
        putBoolean(CHANGE_CONNECT_ANSWER, b)
    }

    fun getPhotoQuality(context: Context): Int {
        initialize(context)
        return getInt(PHOTO_QUALITY, 2)
    }

    fun setPhotoQuality(context: Context, b: Int) {
        initialize(context)
        putInt(PHOTO_QUALITY, b)
    }

    fun getObjectsQuantity(context: Context): Int {
        initialize(context)
        return getInt(OBJECTS_QUANTITY, 5)
    }

    fun setObjectsQuantity(context: Context, b: Int) {
        initialize(context)
        putInt(OBJECTS_QUANTITY, b)
    }

    fun getDaysQuantity(context: Context): Int {
        initialize(context)
        return getInt(DAYS_QUANTITY, 7)
    }

    fun setDaysQuantity(context: Context, b: Int) {
        initialize(context)
        putInt(DAYS_QUANTITY, b)
    }
}