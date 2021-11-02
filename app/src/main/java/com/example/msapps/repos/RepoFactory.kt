package com.example.msapps.repos

import android.app.Application

/**
 * Using a variation of the Factory design pattern to create a scalable solution that can maintain any
 * number of repositories across the app.
 */
object RepoFactory {
    lateinit var context: Application
    val categoryRepo: CategoryRepo = CategoryRepoImpl
    val articleRepo: ArticleRepo = ArticleRepoImpl
    var favoriteRepo: FavoriteRepo = FavoriteRepoImpl
}