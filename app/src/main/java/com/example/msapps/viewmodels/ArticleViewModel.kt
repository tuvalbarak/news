package com.example.msapps.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.msapps.models.Article
import com.example.msapps.models.Category
import com.example.msapps.repos.ArticleRepo
import com.example.msapps.utils.States
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class ArticleViewModel(private val articleRepo: ArticleRepo, app: Application) : AndroidViewModel(app) {

    //Holds the state of the app.
    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }
    //Holds the value of the current category.
    var currCategory = Category.Business

    //Fetching data
    val articlesList = MutableLiveData<List<Article>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            //Getting articles, then initializing the id of each of them (id will be equal to the article url).
            val response = articleRepo.getAllArticlesByCategory(currCategory).apply {
                body()?.articles?.forEach { article ->
                    //Because I added Article some variables that the response doesn't have, they are initialized here.
                    article.id = article.url.toString()
                    article.category = (currCategory.name)
                    article.timeStampAdded = System.currentTimeMillis()
                }
            }
            postValue(response.body()?.articles)
            state.postValue(States.Idle)
        }
    }
    //Using lazy initialization for favoritesList, this way achieving better performance when there's no need to display favorites.
    val favoritesList: MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>().apply {
            viewModelScope.launch(Dispatchers.IO) {
                state.postValue(States.Loading)
                articleRepo.getAllFavorites().collect { favorites ->
                    postValue(favorites)
                    state.postValue(States.Idle)
                }
            }
        }
    }
    //Adding new article to DB.
    fun addArticleToFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            val savedArticle = Article(
                id = article.id,
                isFavorite = article.isFavorite,
                category = article.category,
                timeStampAdded = article.timeStampAdded,
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
        }
        state.postValue(States.Idle)
    }
    //Deleting an article from DB.
    fun deleteFromFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            articleRepo.deleteArticleFromFavorites(article)
            state.postValue(States.DeletedFromFavorites)
        }
        state.postValue(States.Idle)
    }
}