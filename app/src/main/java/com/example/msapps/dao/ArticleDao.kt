package com.example.msapps.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.msapps.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ArticleDao {

    @Insert
    fun insertFavoriteArticle(article: Article)

    @Delete
    fun deleteFavoriteArticle(article: Article)

    @Query("SELECT * FROM favorites ORDER BY id ASC")
    fun getAllFavorites(): Flow<List<Article>>
}