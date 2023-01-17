package com.pyroblinchik.newsfinder.util


import java.text.SimpleDateFormat
import java.util.*

class DateStringFormatter {
    // TODO "M" Add new locals
    private val locale = Locale("ru")
    val dateWithMonthConventional = SimpleDateFormat("MMMM dd, yyyy", locale)
    val dateTimeWithTimeZone = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", locale)
    val simpleDateShort = SimpleDateFormat("dd.MM.yy", locale)
    val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", locale)


    fun parseStringDateToDateTimeWithTimeZone(string: String?): Date? {
        string ?: return null
//        val time = date.time
        return dateTimeWithTimeZone.parse(string)
    }

    fun parseFullInputDateToSimpleDateShort(date: String?): String? {
        date ?: return null
        val time = dateTimeWithTimeZone.parse(date).time
        return simpleDateShort.format(time)
    }


    fun parseStringToSimpleDateFormat(string: String?): Date? {
        string ?: return null
        return simpleDateFormat.parse(string)
    }

    fun parseStringToDateWithMonthConventional(string: String?): Date? {
        string ?: return null
        return dateWithMonthConventional.parse(string)
    }

    fun parseFullInputDateFormatToSimpleDateShortFormat(date: Date?): String? {
        date ?: return null
        val time = date.time
        return simpleDateShort.format(time)
    }

}