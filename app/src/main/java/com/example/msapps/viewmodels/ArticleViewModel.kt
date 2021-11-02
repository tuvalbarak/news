package com.example.msapps.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.msapps.models.Article
import com.example.msapps.repos.ArticleRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Enum class that holds the app state.
enum class States {
    Idle,
    Loading,
    AddedToFavorites
}

class ArticleViewModel(private val articleRepo: ArticleRepo, app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    //Fetching data
    val articlesList = MutableLiveData<List<Article>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)

            val response = articleRepo.getAllArticles()
            Log.d("ArticleViewModel", response.body()?.articles?.size.toString())
            postValue(response.body()?.articles)

//            val list: List<Article> = listOf(
//                    Article(Source("1", "tuval barak"), "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
//                            "https://www.linkedin.com/in/tuval-barak" , "November, 1st 2021", "popo"),
//                    Article(Source("2", "tuval barak"), "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
//                            "https://www.linkedin.com/in/tuval-barak" , "November, 1st 2021", "popo"),
//                    Article(Source("3", "tuval barak"), "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
//                            "https://www.linkedin.com/in/tuval-barak" , "November, 1st 2021", "popo"),
//                    Article(Source("4", "tuval barak"), "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
//                    "https://www.linkedin.com/in/tuval-barak" , "November, 1st 2021", "popo"),
//                    Article(Source("5", "tuval barak"), "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
//                    "https://www.linkedin.com/in/tuval-barak" , "November, 1st 2021", "popo"),
//                    Article(Source("6", "tuval barak"), "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
//                            "https://www.linkedin.com/in/tuval-barak" , "November, 1st 2021", "popo"),
//            )
//            postValue(list)

            state.postValue(States.Idle)
        }
    }

}