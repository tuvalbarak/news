package com.example.msapps

import android.app.Application
import com.example.msapps.repos.RepoFactory

/**
 * Initializer contains necessary data in order to start the app (in this case it seems useless, but I think it's a good practice to use
 * it this way).
 */
object Initializer {
    fun init(application: Application) {
        RepoFactory.context = application
    }
}