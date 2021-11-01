package com.example.msapps.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.msapps.R
import com.example.msapps.models.Article
import kotlinx.android.synthetic.main.holder_row_article.view.*


object ArticleItemDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(article: Article) {

        itemView.apply {

//            holder_row_article_image
            holder_row_article_source.text = article.source
            holder_row_article_published_at.text = article.published_at
            holder_row_article_title.text = article.title
            holder_row_article_description.text = article.description
            holder_row_article_author.text = article.author
            holder_row_article_category.text = article.category


        }
    }
}

class ArticlesAdapter : ListAdapter<Article, ArticlesViewHolder>(ArticleItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder =
        ArticlesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_row_article, parent, false)
        )

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}