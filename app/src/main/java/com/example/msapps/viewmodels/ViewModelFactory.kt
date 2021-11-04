package com.example.msapps.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.msapps.repos.RepoFactory

/**
 * Using the Factory design pattern for a scalable solution to VM approach.
 */
object ViewModelFactory {
    fun create(context: Context) : ViewModelProvider.AndroidViewModelFactory =
            ViewModelFactoryImpl(context.applicationContext as Application)
}

@Suppress("UNCHECKED_CAST")
private class ViewModelFactoryImpl(val app: Application) : ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T = when(modelClass) {
        CategoryViewModel::class.java -> CategoryViewModel(RepoFactory.categoryRepo, app) as T
        ArticleViewModel::class.java -> ArticleViewModel(RepoFactory.articleRepo, app) as T
        else -> throw NotImplementedError(modelClass.toString())
    }
}