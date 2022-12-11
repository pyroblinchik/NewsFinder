package com.pyroblinchik.newsfinder.data.network.services

import com.pyroblinchik.newsfinder.data.network.model.NewsResponceDto
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface NewsService {

    @GET("news")
    suspend fun getNews(
        @Query("access_key") access_key: String?,
        @Query("keywords") keywords: String?,
        @Query("countries") countries: String?,
        @Query("categories") categories: String?,
        @Query("sources") sources: String?,
//        @Query("date") date: String?,
//        @Query("sort") sort: String?,
//        @Query("limit") limit: String?,
//        @Query("offset") offset: String?
        ): NewsResponceDto

}