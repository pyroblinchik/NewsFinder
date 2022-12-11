package com.pyroblinchik.newsfinder.util

import android.content.Context
import com.pyroblinchik.newsfinder.R
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException

object ExceptionParserForAuthorization {

//    fun getMessage(exception: Exception): Int {
//        return when (exception) {
//            is HttpException -> getHttpErrorMessage(exception)
//            else -> generalError()
//        }
//    }
//
//    private fun getHttpErrorMessage(exception: HttpException): Int {
//        return when (exception.code()) {
//            404 -> R.string.error_not_found
//            401 -> {
//                val error = exception.response()?.errorBody()?.string()
//                Timber.e(error)
//                when{
//                    error.toString().contains("invalid_login") -> R.string.login_error
//                    error.toString().contains("invalid_credentials") -> R.string.password_error
//                    else -> generalError()
//                }
//            }
//            else -> generalError()
//        }
//    }
//
//    private fun timeout() : Int{
//        return 10000
//    }
//
//    private fun generalError() = R.string.server_error
}