package com.example.msapps.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.msapps.R
import com.example.msapps.models.Article
import com.example.msapps.models.Category
import com.example.msapps.ui.adapters.ArticlesAdapter
import com.example.msapps.ui.extensions.gone
import com.example.msapps.ui.extensions.show
import com.example.msapps.utils.States
import com.example.msapps.viewmodels.ArticleViewModel
import com.example.msapps.viewmodels.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.android.synthetic.main.holder_row_article.*


class ArticleFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_articles
    override val logTag = "ArticleFragment"

    //Lazy initialization of VM.
    private val articleViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(ArticleViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_articles_tv_title.text = ArticleFragmentArgs.fromBundle(requireArguments()).category
        setupRecyclerView()
        setupState()
        setupArticlesList()
    }

    private fun setupRecyclerView() {
        //Using Implicit Intent to allow the user open the article in his browser.
        val onArticleClicked: (article: Article) -> Unit = { article ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            startActivity(browserIntent)
        }
        //A click on the favorite icon will add the article to the favorites list
        val onFavoriteClicked: (article: Article) -> Unit = { article ->
            article.isFavorite = !article.isFavorite
            holder_row_article_favorite_btn.isActivated = !holder_row_article_favorite_btn.isActivated
            Log.d(logTag, article.title.toString())

            if(article.isFavorite) {
                articleViewModel.addArticleToFavorites(article)
            } else {
                articleViewModel.deleteFromFavorites(article)
            }
        }

        //Binding the adapter with the recyclerview.
        fragment_articles_rv_articles.adapter = ArticlesAdapter(onArticleClicked, onFavoriteClicked)
    }

    private fun displaySnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    /**
     * Observing the current app state.
     * Using View extension functions (cleaner and easier to read) to change the visibility of the progress bar.
     */
    private fun setupState() {

        articleViewModel.state.observe(viewLifecycleOwner, Observer { state ->

            when (state) {
                States.Idle -> {
                    Log.d(logTag, "Idle")
                    fragment_article_pb_progress_bar.gone()
                }
                States.Loading -> {
                    Log.d(logTag, "Loading")
                    fragment_article_pb_progress_bar.show()
                }
                States.AddedToFavorites -> {
                    Log.d(logTag, "AddedToFavorites")
                    fragment_article_pb_progress_bar.gone()
                    displaySnackbar(resources.getString(R.string.snackbar_add_message))
                }
                States.DeletedFromFavorites -> {
                    Log.d(logTag, "DeletedFromFavorites")
                    fragment_article_pb_progress_bar.gone()
                    displaySnackbar(resources.getString(R.string.snackbar_remove_message))
                }
            }
        })
    }

    /**
     * Observing articlesList, which holds the current list that needs to be displayed.
     * Using submitList to achieve a better performance while changes occur.
     */
    private fun setupArticlesList() {
        //If favorites button was clicked on the previous fragment -> display favorites.
        //Getting the value sent via SafeArgs (represents category/favorites from the previous fragment).
        val category = ArticleFragmentArgs.fromBundle(requireArguments()).category

        if(!Category.values().map { it.name }.contains(category)) { //Favorites
            articleViewModel.favoritesList.observe(viewLifecycleOwner, Observer { categoriesList ->
                (fragment_articles_rv_articles.adapter as ArticlesAdapter).submitList(categoriesList)
                //If there are not favorites -> display a message.
                fragment_articles_tv_empty_favorites.apply {
                    if(categoriesList.isEmpty()) show()
                    else gone()
                }
            })
        } else { //Otherwise -> display the selected category.
            articleViewModel.currCategory = Category.valueOf(category) //Parse the category to a Category type.
            articleViewModel.articlesList.observe(viewLifecycleOwner, Observer { categoriesList ->
                (fragment_articles_rv_articles.adapter as ArticlesAdapter).submitList(categoriesList)
            })
        }
    }

}