package com.example.msapps.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.msapps.R
import com.example.msapps.models.Category
import kotlinx.android.synthetic.main.holder_row_welcome.view.*


class UserItemDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}

class CategoriesViewHolder(itemView: View, private val onClickListener: (category: Category) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {

            itemView.apply{

                holder_row_welcome_category.text = category.category

                this.setOnClickListener {
                    category.let { clickedCategory -> onClickListener.invoke(clickedCategory) }
                }

            }




        }
//    fun bind(prevContact: Contact?, currContact: Contact) {
//
//        val prevFirstLetter = prevContact?.firstName?.get(0)?.toString()
//            ?.toUpperCase(Locale.ROOT) //Same for previous contact
//        val currFirstLetter = currContact.firstName[0].toString()
//            .toUpperCase(Locale.ROOT) //Get the first letter of the current contact first name
//
//        itemView.apply {
//            //Drawing a circle which contains the contact's first letter
//            val drawable =
//                TextDrawable.builder().buildRound(currFirstLetter, currContact.backgroundColor)
//            //If the currContact first letter of his first name is different from the previous contact, then display the letter.
//            holder_row_contact_tv_new_letter.text =
//                if (prevFirstLetter != currFirstLetter) currFirstLetter.toUpperCase(Locale.ROOT) //New letter will be displayed
//                else "" //There's no need to display a letter
//
//            //Binding rest of the data.
//            holder_row_contact_tv_first_name.text = currContact.firstName
//            holder_row_contact_tv_last_name.text = currContact.lastName
//            holder_row_contact_tv_phone.text = currContact.phoneNum
//            holder_row_contact_iv_circle.setImageDrawable(drawable) //Daw a circle that contains the first letter of the contact
//
//            this.setOnClickListener { //Setting click listener for each item.
//                currContact.let { clickedContact -> onClickListener.invoke(clickedContact) }
//
//            }
//        }
//    }
}

class CategoriesAdapter(private val onClickListener: (category: Category) -> Unit) : ListAdapter<Category, CategoriesViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_row_welcome, parent, false), onClickListener
        )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}