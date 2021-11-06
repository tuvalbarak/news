package com.example.msapps.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Internal so only relevant module can access
internal lateinit var articlesAPI: ArticlesEndPoints
internal lateinit var categoriesAPI: CategoriesEndPoints

/**
 * Implemented Retrofit creation as a Singleton, means it will only be initialized on its first use.
 * Implemented a generic create function to support code reuse and flexibility.
 */
object RetrofitFactory {

    private const val baseUrl = "https://newsapi.org/"
    private var retrofit: Retrofit? = null

    //Whenever a new endpoints wi be added - it needs to be added to the configure function
    fun configure() {
        articlesAPI = create(ArticlesEndPoints::class.java)
        categoriesAPI = create(CategoriesEndPoints::class.java)
    }

    //Generic function to create the Retrofit service
    private fun <T> create(service: Class<T>): T = retrofit?.create(service) ?: run {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit!!.create(service)
    }
}
