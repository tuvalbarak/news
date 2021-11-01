package com.example.msapps.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msapps.R
import com.example.msapps.models.Article
import com.example.msapps.ui.adapters.ArticlesAdapter
import kotlinx.android.synthetic.main.fragment_articles.*

class ArticleFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_articles
    override val logTag = "ArticleFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupRecyclerView()

    }


    private fun setupRecyclerView() {

        //Divider between items
        fragment_articles_rv_articles.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        //Bind the RecyclerView with the adapter
//        fragment_welcome_rv_category.adapter = CategoryAdapter()
        val list: List<Article> = listOf(
            Article(1, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021"),
            Article(2, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021"),
            Article(3, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021"),
            Article(4, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021"),
            Article(5, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021"),
            Article(6, "tuval barak", "creating app", "lorem ipsum dolorrrrrr", "invalid url", "BBC",
                "https://www.linkedin.com/in/tuval-barak", "general", "en", "Israel", "November, 1st 2021")
        )


        fragment_articles_rv_articles.adapter = ArticlesAdapter()
        (fragment_articles_rv_articles.adapter as ArticlesAdapter).submitList(list)


    }
}