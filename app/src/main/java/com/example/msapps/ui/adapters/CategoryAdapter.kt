package com.example.msapps.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.msapps.R
import com.example.msapps.models.Category
import kotlinx.android.synthetic.main.holder_row_category.view.*

/**
 * Using Diffutils to compare between two articles.
 */
object CategoryItemDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}

/**
 * @property itemView - current item in the recyclerview.
 * @property onClickListener - lambda function for click handling.
 * This class is responsible for binding the data for each row in the recyclerview.
 */
class CategoriesViewHolder(itemView: View, private val onClickListener: (category: Category) -> Unit) : RecyclerView.ViewHolder(itemView) {

    fun bind(category: Category) {

        itemView.apply {
            holder_row_category_tv_category.text = category.toString() //Binding data

            this.setOnClickListener { //Setting click listener for each row
                category.let { clickedCategory -> onClickListener.invoke(clickedCategory) }
            }
        }
    }
}

/**
 * @property onClickListener - lambda function for click handling.
 * Overriding ListAdapter's functions.
 */
class CategoriesAdapter(private val onClickListener: (category: Category) -> Unit) :
    ListAdapter<Category, CategoriesViewHolder>(CategoryItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_row_category, parent, false), onClickListener
        )
    //Could have done binding here as well, chose to do it inside the ViewHolder for cleaner code.
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}