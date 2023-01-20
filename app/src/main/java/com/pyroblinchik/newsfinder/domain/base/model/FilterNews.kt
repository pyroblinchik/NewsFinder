package com.pyroblinchik.newsfinder.domain.base.model


data class FilterNews(
    var sources : List<String>? = null,
    var categories: List<String>? = null,
    var countries: List<String>? = null,
    var languages : List<String>? = null,
    var keywords : List<String>? = null,
    var date: List<String>? = null,
    var sort : List<String>? = null,
    var limit : Int? = null,
    var offset: Int? = null
)
