package com.example.msapps.models

//interface ICategory {
//    val id: Long
//    val category: String
//}
//
//data class Category(
//    override val id: Long,
//    override val category: String
//) : ICategory

//Creating a wrapper class to Category so I can use it in Retrofit
data class CategoryResponse(
        val articles: List<Article>
)

enum class Category {
    Business,
    Entertainment,
    General,
    Health,
    Science,
    Sports,
    Technology
}