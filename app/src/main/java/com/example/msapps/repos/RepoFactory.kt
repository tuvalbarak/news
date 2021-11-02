package com.example.msapps.repos

import android.app.Application

object RepoFactory {

    lateinit var context: Application
    val categoryRepo: CategoryRepo = CategoryRepoImpl
    val articleRepo: ArticleRepo = ArticleRepoImpl
}