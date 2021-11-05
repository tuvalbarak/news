package com.example.msapps.models

import androidx.room.Entity
import androidx.room.PrimaryKey

interface IArticle {
    val author: String?
    val title: String?
    val description: String?
    val url: String?
    val urlToImage: String?
    val publishedAt: String?
    val content: String?
}

@Entity(tableName = "favorites")
data class Article(
    @PrimaryKey
    var id: String,
    var isFavorite: Boolean,
    var category: String,
    var timeStampAdded: Long,
    override val author: String?,
    override val title: String?,
    override val description: String?,
    override val url: String?,
    override val urlToImage: String?,
    override val publishedAt: String?,
    override val content: String?
) : IArticle

//Creating an Article wrapper class so I can use it in Retrofit
data class ArticleResponse(
    val articles: List<Article>
)