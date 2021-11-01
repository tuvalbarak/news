package com.example.msapps.models

interface ICategory {
    val id: Long
    val category: String
}

data class Category(
    override val id: Long,
    override val category: String
) : ICategory