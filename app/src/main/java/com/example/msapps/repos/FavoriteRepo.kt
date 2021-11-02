package com.example.msapps.repos

import com.example.msapps.AppDatabase
import com.example.msapps.models.Article
import kotlinx.coroutines.flow.Flow

/**
 * The Repo contains suspend functions to enable async performance using Coroutines.
 */
interface FavoriteRepo {
    suspend fun getAllFavorites(): Flow<List<Article>>
    suspend fun addArticleToFavorites(article: Article)
    suspend fun deleteFavoriteArticle(article: Article)
}

internal object FavoriteRepoImpl : FavoriteRepo {

    override suspend fun getAllFavorites() : Flow<List<Article>> =
        AppDatabase.instance().articleDao.getAllFavorites()

    override suspend fun addArticleToFavorites(article: Article) {
        AppDatabase.instance().articleDao.insertFavoriteArticle(article)
    }

    override suspend fun deleteFavoriteArticle(article: Article) {
        AppDatabase.instance().articleDao.deleteFavoriteArticle(article)
    }

}