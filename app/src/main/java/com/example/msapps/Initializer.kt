package com.example.msapps

import android.app.Application
import com.example.msapps.remote.RetrofitFactory
import com.example.msapps.repos.RepoFactory

/**
 * Initializer contains necessary data in order to start the app.
 */
object Initializer {
    fun init(application: Application) {
        //Initializing the app context
        RepoFactory.context = application
        //Creating the required retrofit services
        RetrofitFactory.configure()
    }
}