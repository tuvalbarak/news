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
import com.example.msapps.viewmodels.ArticleViewModel
import com.example.msapps.viewmodels.States
import com.example.msapps.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_articles.*


class ArticleFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_articles
    override val logTag = "ArticleFragment"

    private val articleViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(ArticleViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupState()
        setupArticlesList()
    }


    private fun setupRecyclerView() {
        fragment_articles_rv_articles.adapter = ArticlesAdapter()
    }


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


    private fun setupArticlesList() {
        articleViewModel.articlesList.observe(viewLifecycleOwner, Observer { categoriesList ->
            (fragment_articles_rv_articles.adapter as ArticlesAdapter).submitList(categoriesList)
        })
    }
}