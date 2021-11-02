package com.example.msapps

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.msapps.dao.ArticleDao
import com.example.msapps.models.Article
import com.example.msapps.repos.RepoFactory

private const val DB_NAME = "favorites"

/**
 * DB reference is implemented as a Singleton, gaining a single source of truth throughout the app.
 */
@Database(entities = [
    Article::class
], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao

    companion object {
        var dbInstance: AppDatabase? = null

        fun instance() = dbInstance ?: run {
            dbInstance = Room.databaseBuilder(RepoFactory.context, AppDatabase::class.java, DB_NAME).build()
            dbInstance!!
        }
    }
}

