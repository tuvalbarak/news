package com.example.msapps.ui.fragments

import android.annotation.SuppressLint
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
import com.example.msapps.ui.extensions.displaySnackbar
import com.example.msapps.ui.extensions.gone
import com.example.msapps.ui.extensions.show
import com.example.msapps.utils.States
import com.example.msapps.viewmodels.ArticleViewModel
import com.example.msapps.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_articles.*


class ArticleFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_articles
    override val logTag = "ArticleFragment"

    //Lazy initialization of VM.
    private val articleViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(ArticleViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_articles_tv_title.text = ArticleFragmentArgs.fromBundle(requireArguments()).category //Setting the page's title
        setupRecyclerView()
        setupState()
        setupArticlesList()
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun setupRecyclerView() {
        //Using Implicit Intent to allow the user open the article in his browser.
        val onArticleClicked: (article: Article) -> Unit = { article ->

            Intent(Intent.ACTION_VIEW, Uri.parse(article.url)).apply {
                    startActivity(this)
            }
        }
        //A click on the favorite icon will add the article to the favorites list
        val onFavoriteClicked: (article: Article) -> Unit = { article ->
            articleViewModel.onFavoriteToggled(article)
            //NOTE - I know I shouldn't put notifyDataSetChanged here, but there's a known bug in google's submitList
            // function (it won't call diffutils if only part of the object was changed - isFavorite), so after I made my research I decided that
            // this is the best solution for this particular problem. Link to a discussion about the bug -
            // https://stackoverflow.com/questions/49726385/listadapter-not-updating-item-in-recyclerview
            (fragment_articles_rv_articles.adapter as ArticlesAdapter).notifyDataSetChanged()
        }

        //Binding the adapter with the recyclerview.
        fragment_articles_rv_articles.adapter = ArticlesAdapter(onArticleClicked, onFavoriteClicked)
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
            articleViewModel.favoritesList.observe(viewLifecycleOwner, Observer { favoritesList ->
                (fragment_articles_rv_articles.adapter as ArticlesAdapter).submitList(favoritesList)
                displayMessageEmptyList(favoritesList, resources.getString(R.string.no_articles_favorites))
            })
        } else { //Otherwise -> display the selected category.
            articleViewModel.currCategory = Category.valueOf(category) //Parse the category to a Category type.
            articleViewModel.articlesList.observe(viewLifecycleOwner, Observer { articlesList ->
                (fragment_articles_rv_articles.adapter as ArticlesAdapter).submitList(articlesList)
                displayMessageEmptyList(articlesList, resources.getString(R.string.no_articles_api))
            })
        }
    }

    /**
     * If the retrieved list is empty -> display a message
     */
    private fun displayMessageEmptyList(list: List<Article>, message: String) {
        fragment_articles_tv_empty_favorites.apply {
            if (list.isEmpty()) {
                fragment_articles_tv_empty_favorites.text = message
                show()
            } else {
                gone()
            }
        }
    }

}