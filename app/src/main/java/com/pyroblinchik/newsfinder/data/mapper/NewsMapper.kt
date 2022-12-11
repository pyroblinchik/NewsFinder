package com.pyroblinchik.newsfinder.data.mapper

import com.pyroblinchik.newsfinder.data.network.model.NewsResponceDto
import com.pyroblinchik.newsfinder.domain.base.model.News
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class NewsMapper @Inject constructor() {


    fun mapNewsDtoModelToEntity(response: NewsResponceDto): ArrayList<News> {
        val result = ArrayList<News>()
        var fakeID = 0
        response.data?.forEach {
            it.id = fakeID
            result.add(it)
            fakeID++
        }
        return result
    }
}






