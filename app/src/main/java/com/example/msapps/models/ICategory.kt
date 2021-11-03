package com.example.msapps.models

enum class Category {
    Business,
    Entertainment,
    General,
    Health,
    Science,
    Sports,
    Technology
}

//Creating a wrapper class to Category so I can use it in Retrofit
data class CategoryResponse(
        val articles: List<Article>
)

