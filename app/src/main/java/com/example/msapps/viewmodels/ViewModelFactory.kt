package com.example.msapps.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelFactory {
    fun create(context: Context) : ViewModelProvider.AndroidViewModelFactory =
            ViewModelFactoryImpl(context.applicationContext as Application)
}

@Suppress("UNCHECKED_CAST")
private class ViewModelFactoryImpl(val app: Application) : ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T = when(modelClass) {

        else -> throw NotImplementedError(modelClass.toString())
    }

}