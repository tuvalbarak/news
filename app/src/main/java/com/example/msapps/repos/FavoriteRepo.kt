package com.example.msapps.repos

import com.example.msapps.AppDatabase
import com.example.msapps.models.Article
import kotlinx.coroutines.flow.Flow

/**
 * The Repo contains suspend functions to enable async performance using Coroutines.
 */
interface FavoriteRepo {

    suspend fun getAllFavorites(): Flow<List<Article>>

}

internal object FavoriteRepoImpl : FavoriteRepo {

    override suspend fun getAllFavorites() : Flow<List<Article>> =
        AppDatabase.instance().articleDao.getAllFavorites()


}