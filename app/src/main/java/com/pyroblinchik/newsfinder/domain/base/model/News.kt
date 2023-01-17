package com.pyroblinchik.newsfinder.domain.base.model


// TODO "I" change to data classes
class News{
    constructor()
    constructor(
        id: Int,
        author: String?,
        title: String?,
        description: String?,
        url: String?,
        source: String?,
        image: String?,
        category: String?,
        language: String?,
        country: String?,
        published_at: String?,
        isFavorite: Boolean,
        isInHistory: Boolean,
        historyGroupId: Int,
        historyDate: String?
    ) {
        this.id = id
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.source = source
        this.image = image
        this.category = category
        this.language = language
        this.country = country
        this.published_at = published_at
        this.isFavorite = isFavorite
        this.isInHistory = isInHistory
        this.historyGroupId = historyGroupId
        this.historyDate = historyDate
    }


    var id: Int = 0
    var author : String? = null
    var title: String? = null
    var description: String? = null
    var url : String? = null
    var source: String? = null
    var image : String? = null
    var category: String? = null
    var language : String? = null
    var country : String? = null
    var published_at: String? = null

    /// Local

    var isFavorite: Boolean = false
    var isInHistory: Boolean = false
    var historyGroupId : Int = 0
    var historyDate : String? = null

    override fun toString(): String {
        return "News(id=$id, author=$author, title=$title, description=$description, url=$url, source=$source, image=$image, category=$category, language=$language, country=$country, published_at=$published_at, isFavorite=$isFavorite, historyGroupId=$historyGroupId)"
    }

}
