package com.pyroblinchik.newsfinder.domain.base.model


class Pagination{
    constructor()
    constructor(limit: Int?, offset: Int?, count: Int?, total: Int?) {
        this.limit = limit
        this.offset = offset
        this.count = count
        this.total = total
    }


    var limit: Int? = null
    var offset: Int? = null
    var count: Int? = null
    var total: Int? = null

}
