package com.example.msapps.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.msapps.R
import com.example.msapps.models.Article
import kotlinx.android.synthetic.main.holder_row_article.view.*

/**
 * Using Diffutils to compare between two articles.
 */
object ArticleItemDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

/**
 * @property itemView - current item in the recyclerview.
 * This class is responsible for binding the data for each row in the recyclerview.
 */
class ArticlesViewHolder(itemView: View, private val onFavoriteClickListener: (article: Article) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    //Binding data
    fun bind(article: Article) {

        itemView.apply {
            Glide.with(context).load(article.urlToImage).into(holder_row_article_image)
//            holder_row_article_source.text = article.source?.name
            holder_row_article_published_at.text = article.publishedAt
            holder_row_article_title.text = article.title
            holder_row_article_description.text = article.description
            holder_row_article_author.text = article.author
            holder_row_article_category.text = "categoryyyy"
            //didnt bind content

//            holder_row_article_favorite_btn.setOnClickListener { //Toggles the favorite button state
//                holder_row_article_favorite_btn.isActivated = !holder_row_article_favorite_btn.isActivated
//            }
            holder_row_article_favorite_btn.setOnClickListener {
                article.let { favoriteClicked -> onFavoriteClickListener.invoke(favoriteClicked)}
            }
        }
    }
}

/**
 * Overriding ListAdapter's functions.
 */
class ArticlesAdapter(private val onFavoriteClickListener: (article: Article) -> Unit) :
    ListAdapter<Article, ArticlesViewHolder>(ArticleItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder =
        ArticlesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_row_article, parent, false), onFavoriteClickListener
        )
    //Could have done binding here as well, chose to do it inside the ViewHolder for cleaner code.
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}