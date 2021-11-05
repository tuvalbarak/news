package com.example.msapps.dao

import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Delete
import com.example.msapps.models.Article
import kotlinx.coroutines.flow.Flow

/**
 * DAO - Data Access Object - Gives access to Room DB.
 */
@Dao
internal interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteArticle(article: Article)

    @Delete
    fun deleteFavoriteArticle(article: Article)

    @Query("SELECT * FROM favorites ORDER BY publishedAt ASC")
    fun getAllFavorites(): Flow<List<Article>>
}