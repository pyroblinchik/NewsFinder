package com.pyroblinchik.newsfinder.util

import com.pyroblinchik.newsfinder.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

object ExceptionParser {

    const val timeoutCode = "change_connect"

    fun getMessage(exception: Exception): String {

        return when (exception) {
            is HttpException -> {
                "${exception.response()}"
            }
            is SocketTimeoutException -> {
                timeoutCode
            }
            is ConnectException -> {
                timeoutCode
            }
            else -> (exception.message ?: exception.cause?.stackTrace ?: "").toString()
        }
    }

//    private fun generalError() = R.string.server_error
}