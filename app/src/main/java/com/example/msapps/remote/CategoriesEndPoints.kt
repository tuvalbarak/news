package com.example.msapps.remote

import com.example.msapps.models.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Note - NewsAPI doesn't let us query the available categories, so this interface isn't used at all. I wrote it to show how I would use it
 * if I could query the categories...
 */
interface CategoriesEndPoints {
    @GET("v2/categories?apiKey=dbb965a3892e4e948ef96bcb3ee21501")
    suspend fun getAllCategories(
            @Query("apiKey") apiKey: String
    ): Response<CategoryResponse>
}