package com.pyroblinchik.newsfinder.domain.base.model


class FilterNews{
    constructor()
    constructor(
        sources: List<String>?,
        categories: List<String>?,
        countries: List<String>?,
        languages: List<String>?,
        keywords: List<String>?,
        date: List<String>?,
        sort: List<String>?,
        limit: Int?,
        offset: Int?
    ) {
        this.sources = sources
        this.categories = categories
        this.countries = countries
        this.languages = languages
        this.keywords = keywords
        this.date = date
        this.sort = sort
        this.limit = limit
        this.offset = offset
    }

    var sources : List<String>? = null
    var categories: List<String>? = null
    var countries: List<String>? = null
    var languages : List<String>? = null
    var keywords : List<String>? = null
    var date: List<String>? = null
    var sort : List<String>? = null
    var limit : Int? = null
    var offset: Int? = null

}
