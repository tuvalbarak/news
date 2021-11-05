package com.example.msapps.remote

import com.example.msapps.models.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesEndPoints {

    @GET("v2/top-headlines")
    suspend fun getAllArticlesByCategory(
            @Query("category") category: String,
            @Query("country") country: String,
            @Query("apiKey") apiKey: String
    ): Response<ArticleResponse>
}

