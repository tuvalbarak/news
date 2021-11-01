package com.example.msapps

import android.app.Application
import com.example.msapps.repos.RepoFactory

object Initializer {
    fun init(application: Application) {
        RepoFactory.context = application
    }

}