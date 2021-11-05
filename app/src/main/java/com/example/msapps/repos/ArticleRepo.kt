package com.example.msapps.repos

import com.example.msapps.AppDatabase
import com.example.msapps.models.Article
import com.example.msapps.models.ArticleResponse
import com.example.msapps.models.Category
import com.example.msapps.remote.ApiInterface
import com.example.msapps.remote.ArticlesEndPoints
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * The Repo contains suspend functions to enable async performance using Coroutines.
 */
interface ArticleRepo {
    suspend fun getAllArticlesByCategory(category: Category): Response<ArticleResponse>
    suspend fun getAllFavorites(): Flow<List<Article>>
    suspend fun getFavoriteById(id: String): Article?
    suspend fun addArticleToFavorites(article: Article)
    suspend fun deleteArticleFromFavorites(article: Article)
}

internal object ArticleRepoImpl : ArticleRepo {
    private const val COUNTRY = "us"
    private const val API_KEY = "dbb965a3892e4e948ef96bcb3ee21501"
    override suspend fun getAllArticlesByCategory(category: Category) =
        ApiInterface
            .create(ArticlesEndPoints::class.java)
            .getAllArticlesByCategory(category.toString(), COUNTRY, API_KEY)

    override suspend fun getAllFavorites(): Flow<List<Article>> =
        AppDatabase.instance().articleDao.getAllFavorites()

    override suspend fun getFavoriteById(id: String) =
        AppDatabase.instance().articleDao.getFavoriteById(id)

    override suspend fun addArticleToFavorites(article: Article) {
        AppDatabase.instance().articleDao.insertFavoriteArticle(article)
    }

    override suspend fun deleteArticleFromFavorites(article: Article) {
        AppDatabase.instance().articleDao.deleteFavoriteArticle(article)
    }

}