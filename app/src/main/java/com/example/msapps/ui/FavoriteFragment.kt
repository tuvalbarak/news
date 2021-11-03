package com.example.msapps.ui

import android.content.Intent
import android.net.Uri
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
import com.example.msapps.viewmodels.FavoriteViewModel
import com.example.msapps.viewmodels.States
import com.example.msapps.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.holder_row_article.*

class FavoriteFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_favorites
    override val logTag = "FavoriteFragment"

    private val favoriteViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(FavoriteViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragment_favorites_tv_title.text = "Favorites" //Setting Title
        setupRecyclerView()
        setupState()
        setupArticlesList()

    }

    private fun setupRecyclerView() {

        val onArticleClicked: (article: Article) -> Unit = { article ->
            startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            )
        }

        val onFavoriteClicked: (article: Article) -> Unit = { article ->
            article.isFavorite = !article.isFavorite
            holder_row_article_favorite_btn.isActivated = !holder_row_article_favorite_btn.isActivated
            favoriteViewModel.deleteFromFavorites(article)
            Log.d(logTag, article.title.toString())
        }

        //Binding the adapter with the recyclerview.
        fragment_favorites_rv_articles.adapter = ArticlesAdapter(onArticleClicked, onFavoriteClicked)
    }

    /**
     * Observing the current app state.
     * Using View extension functions (cleaner and easier to read) to change the visibility of the progress bar.
     */
    private fun setupState() {

        favoriteViewModel.state.observe(viewLifecycleOwner, Observer { state ->

            when (state) {
                States.Idle -> {
                    Log.d(logTag, "Idle")
                    fragment_favorites_pb_progress_bar.gone()
                }
                States.Loading -> {
                    Log.d(logTag, "Loading")
                    fragment_favorites_pb_progress_bar.show()
                }
                States.AddedToFavorites -> {
                    Log.d(logTag, "AddedToFavorites")
                    fragment_favorites_pb_progress_bar.gone()
                }
                States.DeletedFromFavorites -> {
                    Log.d(logTag, "DeletedFromFavorites")
                    fragment_favorites_pb_progress_bar.gone()
                }
            }
        })
    }

    /**
     * Observing articlesList, which holds the current list that needs to be displayed.
     * Using submitList to achieve a better performance while changes occur.
     */
    private fun setupArticlesList() {
        favoriteViewModel.favoritesList.observe(viewLifecycleOwner, Observer { favorites ->
            (fragment_favorites_rv_articles.adapter as ArticlesAdapter).submitList(favorites)
        })
    }

}