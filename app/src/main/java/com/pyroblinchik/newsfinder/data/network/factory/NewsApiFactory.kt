package com.pyroblinchik.newsfinder.data.network.factory

import android.util.Log
import com.google.gson.GsonBuilder
import com.pyroblinchik.newsfinder.data.Constants.ip
import com.pyroblinchik.newsfinder.data.network.services.NewsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsApiFactory {
    var gson = GsonBuilder()
        .setLenient()
        .create()
    private val httpClient =
        OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request =
                original.newBuilder()
                    .method(original.method, original.body)
//                    .addHeader("Content-Type","application/json")
                    .build()
            Log.d("Клиент", request.toString())
            chain.proceed(request)
        })
    private val client = httpClient.build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(ip)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()


    val apiService = retrofit.create(NewsService::class.java)
}
