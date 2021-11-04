package com.example.msapps.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.msapps.R
import com.example.msapps.models.Article
import com.example.msapps.utils.currentCategory
import kotlinx.android.synthetic.main.holder_row_article.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


/**
 * Using Diffutils to compare between two articles.
 */
object ArticleItemDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }
}

/**
 * @property itemView - current item in the recyclerview.
 * @property onArticleClickListener - lambda function for click handling.
 * @property onFavoriteClickListener - lambda function for clicks on favorites icon.
 * This class is responsible for binding the data for each row in the recyclerview.
 */
class ArticlesViewHolder(itemView: View,
                         private val onArticleClickListener: (article: Article) -> Unit,
                         private val onFavoriteClickListener: (article: Article) -> Unit) : RecyclerView.ViewHolder(itemView) {

    //Binding data
    fun bind(article: Article) {

        itemView.apply {
            //Parsing date
            var date = article.publishedAt
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val timestampAsDateString = article.publishedAt
                val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
                date = LocalDate.parse(timestampAsDateString, format).toString()
            }

            holder_row_article_author.text = article.author
            holder_row_article_title.text = article.title
            holder_row_article_description.text = article.description
            holder_row_article_published_at.text =  date
            holder_row_article_category.text = currentCategory
            holder_row_article_favorite_btn.isActivated = article.isFavorite

            Glide.with(context).load(article.urlToImage).into(holder_row_article_image)

            this.setOnClickListener {
                article.let { articleClicked -> onArticleClickListener.invoke(articleClicked)}
            }

            holder_row_article_favorite_btn.setOnClickListener {
                article.let { favoriteClicked -> onFavoriteClickListener.invoke(favoriteClicked)}
            }
        }
    }
}

/**
 * Overriding ListAdapter's functions.
 */
class ArticlesAdapter(private val onArticleClickListener: (article: Article) -> Unit,
                      private val onFavoriteClickListener: (article: Article) -> Unit) :
    ListAdapter<Article, ArticlesViewHolder>(ArticleItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder =
        ArticlesViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.holder_row_article, parent, false),
                onArticleClickListener, onFavoriteClickListener
        )
    //Could have done binding here as well, chose to do it inside the ViewHolder for cleaner code.
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}