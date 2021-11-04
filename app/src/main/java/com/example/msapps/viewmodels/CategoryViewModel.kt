package com.example.msapps.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.msapps.models.Category
import com.example.msapps.repos.CategoryRepo
import com.example.msapps.utils.States
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Note: even though I don't use the categoryRepo, I still get it in the constructor to demonstrate a situation where I get the various categories from the API.
 */
class CategoryViewModel(private val categoryRepo: CategoryRepo, app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    val categoriesList = MutableLiveData<List<Category>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            //Getting all the possible categories, casting it to List so it can be inside postValue.
            val listOfCategories = enumValues<Category>()
            postValue(listOfCategories.toList())
            state.postValue(States.Idle)
        }
    }

}