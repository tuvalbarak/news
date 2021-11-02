package com.example.msapps.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.msapps.models.Category
import com.example.msapps.repos.CategoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepo: CategoryRepo, app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    val categoriesList = MutableLiveData<List<Category>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            //Here I need to call repo to fetch data
            val list: List<Category> = listOf(
                Category(1,"first"),
                Category(2,"second"),
                Category(3,"third"),
                Category(4,"fourth"),
                Category(5,"fifth")
            )
            postValue(list)
            state.postValue(States.Idle)
        }
    }

}