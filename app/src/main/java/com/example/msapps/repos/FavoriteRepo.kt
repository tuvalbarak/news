package com.example.msapps.repos

import com.example.msapps.models.Article
import kotlinx.coroutines.flow.Flow

/**
 * The Repo contains suspend functions to enable async performance using Coroutines.
 */
interface FavoriteRepo {

    suspend fun getAllFavorites(): Flow<List<Article>>

//    suspend fun getAllArticles(): Response<ArticleResponse>
}

internal object FavoriteRepoImpl : FavoriteRepo {

    override suspend fun getAllFavorites(): Flow<List<Article>> {

    }
//    override suspend fun getAllArticles() =
//        ServiceBuilder.buildService(ArticlesEndPoints::class.java).getAllArticles(currentCategory.toLowerCase(Locale.ROOT), "us",
//            "dbb965a3892e4e948ef96bcb3ee21501")
}