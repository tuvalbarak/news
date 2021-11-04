package com.example.msapps.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.msapps.models.Article
import com.example.msapps.repos.ArticleRepo
import com.example.msapps.utils.States
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class ArticleViewModel(private val articleRepo: ArticleRepo, app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    //Fetching data
    val articlesList = MutableLiveData<List<Article>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            val response = articleRepo.getAllArticles()
            postValue(response.body()?.articles)
            state.postValue(States.Idle)
        }
    }
    //Using lazy initialization for favoritesList, this way achieving better performance when there's no need to display favorites.
    val favoritesList: MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>().apply {
            viewModelScope.launch(Dispatchers.IO) {
                state.postValue(States.Loading)
                articleRepo.getFavoritesArticles().collect { favorites ->
                    postValue(favorites)
                    state.postValue(States.Idle)
                }
            }
        }
    }

    fun addArticleToFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            val savedArticle = Article(
                id = System.currentTimeMillis(),
                isFavorite = article.isFavorite,
                author = article.author,
                title = article.title,
                description = article.description,
                url = article.url,
                urlToImage = article.urlToImage,
                publishedAt = article.publishedAt,
                content = article.content
            )
            articleRepo.addArticleToFavorites(savedArticle)
            state.postValue(States.AddedToFavorites)
            state.postValue(States.Idle)
        }
    }

    fun deleteFromFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            articleRepo.deleteFavoriteArticle(article)
            state.postValue(States.DeletedFromFavorites)
            state.postValue(States.Idle)
            Log.d("ArticleFragment", "5")
        }
    }
}