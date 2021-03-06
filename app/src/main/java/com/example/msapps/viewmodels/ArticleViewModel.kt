package com.example.msapps.viewmodels

import android.app.Application
import android.util.Log
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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

class ArticleViewModel(private val articleRepo: ArticleRepo, app: Application) : AndroidViewModel(app) {

    //Holds the state of the app.
    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }
    //Holds the value of the current category.
    var currCategory: Category? = null
    //In case currCategory is null for some reason, defaultCategory will be used instead.
    private val defaultCategory = Category.Business

    //Fetching data
    val articlesList = MutableLiveData<List<Article>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            //Getting articles, then initializing the id of each of them (id will be equal to the article url).
            val articles = articleRepo.getAllArticlesByCategory(currCategory ?: defaultCategory)?.onEach { article ->
                //Because I added to Article some variables that the API response doesn't have, they are initialized here.
                article.id = article.url.toString()
                article.category = (currCategory?.name ?: defaultCategory.name)
                article.timeStampAdded = System.currentTimeMillis()

                //If an article is on the user's favorites, assign isFavorite to true.
                articleRepo.getFavoriteById(article.id)?.id?.let {
                    article.isFavorite = true
                }
            }
            postValue(articles)
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
    private fun addArticleToFavorites(article: Article) {
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
    private fun deleteFromFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            articleRepo.deleteArticleFromFavorites(article)
            state.postValue(States.DeletedFromFavorites)
        }
        state.postValue(States.Idle)
    }
    //Toggles the favorite state.
    fun onFavoriteToggled(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)

            if (article.isFavorite) { //It's on -> need to delete
                deleteFromFavorites(article)
            } else { //It's off -> need to add
                addArticleToFavorites(article)
            }
            //Updating the changed item
            val articles = articlesList.value
            articles?.map { newArticle ->
                if (newArticle.id == article.id) {
                    newArticle.isFavorite = !article.isFavorite
                }
            }
            articlesList.postValue(articles)
            state.postValue(States.Idle)
        }
    }
}