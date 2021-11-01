package com.example.msapps.models


interface IArticle {
    val id: Long
    val author: String
    val title: String
    val description: String
    val url: String
    val source: String
    val image: String
    val category: String
    val language: String
    val country: String
    val published_at: String
    val isFavorite: Boolean
}

data class Article(
    override val id: Long,
    override val author: String,
    override val title: String,
    override val description: String,
    override val url: String,
    override val source: String,
    override val image: String,
    override val category: String,
    override val language: String,
    override val country: String,
    override val published_at: String,
    override val isFavorite: Boolean

) : IArticle




//"author": "Dan Peck",
//"title": "Hurricane Hanna makes landfall around 5 p.m. on Saturday.",
//"description": "Hurricane Hanna battered southern Texas with sustained winds of 75 mph and continued to deliver heavy rain and flash flooding as it moved inland late Saturday.",
//"url": "https://abcnews.go.com/US/hurricane-hanna-makes-landfall-texas/story?id=71985566",
//"source": "ABC News",
//"image": "https://s.abcnews.com/images/US/hanna-swimmer-mo_hpMain_20200725-163152_2_4x3t_384.jpg",
//"category": "general",
//"language": "en",
//"country": "us",
//"published_at": "2020-07-26T01:04:23+00:00"