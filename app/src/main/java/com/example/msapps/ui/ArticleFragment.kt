package com.example.msapps.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.msapps.R
import com.example.msapps.models.Article
import com.example.msapps.ui.adapters.ArticlesAdapter
import com.example.msapps.ui.extensions.gone
import com.example.msapps.ui.extensions.show
import com.example.msapps.utils.currentCategory
import com.example.msapps.viewmodels.ArticleViewModel
import com.example.msapps.viewmodels.States
import com.example.msapps.viewmodels.ViewModelFactory
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
        fragment_articles_tv_title.text = currentCategory //Setting Title
        setupRecyclerView()
        setupState()
        setupArticlesList()
    }

    private fun setupRecyclerView() {
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
        fragment_articles_rv_articles.adapter = ArticlesAdapter(onFavoriteClicked)
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
                    fragment_category_pb_progress_bar.gone()
                }
                States.Loading -> {
                    Log.d(logTag, "Loading")
                    fragment_category_pb_progress_bar.show()
                }
                States.AddedToFavorites -> {
                    Log.d(logTag, "AddedToFavorites")
                    fragment_category_pb_progress_bar.gone()
                }
            }
        })
    }

    /**
     * Observing articlesList, which holds the current list that needs to be displayed.
     * Using submitList to achieve a better performance while changes occur.
     */
    private fun setupArticlesList() {
        articleViewModel.articlesList.observe(viewLifecycleOwner, Observer { categoriesList ->
            (fragment_articles_rv_articles.adapter as ArticlesAdapter).submitList(categoriesList)
        })
    }
}