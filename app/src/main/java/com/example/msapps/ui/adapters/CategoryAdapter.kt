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


object CategoryItemDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}

class CategoriesViewHolder(itemView: View, private val onClickListener: (category: Category) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {

            itemView.apply {

                holder_row_category_tv_category.text = category.category

                this.setOnClickListener {
                    category.let { clickedCategory -> onClickListener.invoke(clickedCategory) }
                }

            }
        }
}

class CategoriesAdapter(private val onClickListener: (category: Category) -> Unit) :
    ListAdapter<Category, CategoriesViewHolder>(CategoryItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_row_category, parent, false), onClickListener
        )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}