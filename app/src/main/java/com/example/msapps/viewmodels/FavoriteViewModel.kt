package com.example.msapps.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.msapps.models.Article
import com.example.msapps.repos.FavoriteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class FavoriteViewModel(private val favoriteRepo: FavoriteRepo, app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    //Fetching data
    val favoritesList = MutableLiveData<List<Article>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
           favoriteRepo.getAllFavorites().collect { favorites ->
                postValue(favorites)
               state.postValue(States.Idle)
           }
        }
    }

    //Removing article from favorites.
    fun deleteFromFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            favoriteRepo.deleteFavoriteArticle(article)
            state.postValue(States.DeletedFromFavorites)
            state.postValue(States.Idle)
        }
    }


}
