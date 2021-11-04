package com.example.msapps.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Implemented Retrofit creation as a Singleton, means it will only be initialized in its first use.
 * Implemented the create function as generic to support code reuse and flexibility.
 */
internal interface ApiInterface {

    companion object {

        private const val baseUrl = "https://newsapi.org/"
        private var retrofit: Retrofit? = null

        fun <T> create(service: Class<T>): T = retrofit?.create(service) ?: run {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            retrofit!!.create(service)
        }
    }
}