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
    //If a user tries to add an already existing article, replace it with the new one.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteArticle(article: Article)

    @Delete
    fun deleteFavoriteArticle(article: Article)

    //Sorting favorites from latest to oldest
    @Query("SELECT * FROM favorites ORDER BY timeStampAdded DESC")
    fun getAllFavorites(): Flow<List<Article>>
}