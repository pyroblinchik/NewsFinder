package com.pyroblinchik.newsfinder.data.network.model

import com.pyroblinchik.newsfinder.domain.base.model.News
import com.pyroblinchik.newsfinder.domain.base.model.Pagination

class NewsResponceDto {
    val pagination: Pagination? = null
    val data: ArrayList<News>? = null
}
