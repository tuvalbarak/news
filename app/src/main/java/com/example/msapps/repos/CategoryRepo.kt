package com.example.msapps.repos

import com.example.msapps.models.CategoryResponse
import com.example.msapps.remote.categoriesAPI
import retrofit2.Response

/**
 * Even though I don't use the CategoryRepo in this app, I still built the required structure in order to use it.
 */
interface CategoryRepo {
    suspend fun getAllCategories(): Response<CategoryResponse> //Example function
}

internal object CategoryRepoImpl : CategoryRepo {
    private const val API_KEY = "dbb965a3892e4e948ef96bcb3ee21501"
    override suspend fun getAllCategories() =
            categoriesAPI.getAllCategories(API_KEY)

}