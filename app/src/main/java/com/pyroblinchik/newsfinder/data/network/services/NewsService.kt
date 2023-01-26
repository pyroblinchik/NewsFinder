package com.pyroblinchik.newsfinder.data.network.services

import com.pyroblinchik.newsfinder.data.network.model.NewsResponseDto
import retrofit2.http.*

// TODO
//  |CONSIDER| putting dagger-hilt injection to "News Service,
//  |CONSIDER| wrap response from API to Response<> wrapper, for safety reason
/*
NewsService is the interface to define API routes application accesses
 */
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
        ): NewsResponseDto

}