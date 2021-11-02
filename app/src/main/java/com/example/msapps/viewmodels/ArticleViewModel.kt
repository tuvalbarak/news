package com.example.msapps.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.msapps.models.Article
import com.example.msapps.repos.ArticleRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


enum class States {
    Idle,
    Loading,
    AddedToFavorites
}

class ArticleViewModel(private val articleRepo: ArticleRepo, app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    val articlesList = MutableLiveData<List<Article>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            //Here I need to call repo to fetch data
            val list: List<Article> = listOf(
                Article(1, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                    "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021", false),
                Article(2, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                    "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021", true),
                Article(3, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                    "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021", false),
                Article(4, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                    "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021", false),
                Article(5, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                    "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021", true),
                Article(6, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                    "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021", false)
            )
            postValue(list)
            state.postValue(States.Idle)
        }
    }



}