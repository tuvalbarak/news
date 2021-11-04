package com.example.msapps.remote

import com.example.msapps.models.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesEndPoints {
    @GET("v2/everything?apiKey=dbb965a3892e4e948ef96bcb3ee21501")
    suspend fun getAllCategories(
            @Query("apiKey") apiKey: String
    ): Response<CategoryResponse>
}