package com.pyroblinchik.newsfinder.domain.base.model


data class Pagination(
    var limit: Int? = null,
    var offset: Int? = null,
    var count: Int? = null,
    var total: Int? = null
)
