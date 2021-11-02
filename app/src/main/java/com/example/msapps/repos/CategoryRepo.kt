package com.example.msapps.repos

import com.example.msapps.models.CategoryResponse
import com.example.msapps.remote.ArticlesEndPoints
import com.example.msapps.remote.CategoriesEndPoints
import com.example.msapps.remote.ServiceBuilder
import retrofit2.Response
import retrofit2.http.GET


interface CategoryRepo {
//    suspend fun getAllCategories() : Response<CategoryResponse>
    suspend fun getAllCategories(): Response<CategoryResponse>
}

internal object CategoryRepoImpl : CategoryRepo {
    override suspend fun getAllCategories() =
            ServiceBuilder.buildService(CategoriesEndPoints::class.java).getAllArticles()



}