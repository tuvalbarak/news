package com.example.msapps.remote

import com.example.msapps.models.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesEndPoints {

    @GET("v2/everything?apiKey=dbb965a3892e4e948ef96bcb3ee21501")
    suspend fun getAllArticles(): Response<CategoryResponse>

}