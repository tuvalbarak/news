package com.example.msapps.repos

import com.example.msapps.models.ArticleResponse
import com.example.msapps.remote.ArticlesEndPoints
import com.example.msapps.remote.ServiceBuilder
import com.example.msapps.utils.currentCategory

import retrofit2.Response
import java.util.*

/**
 * The Repo contains suspend functions to enable async performance using Coroutines.
 */
interface ArticleRepo {
    suspend fun getAllArticles(): Response<ArticleResponse>
}

internal object ArticleRepoImpl : ArticleRepo {
    override suspend fun getAllArticles() =
        ServiceBuilder.buildService(ArticlesEndPoints::class.java).getAllArticles(currentCategory.toLowerCase(Locale.ROOT), "us",
            "dbb965a3892e4e948ef96bcb3ee21501")
}