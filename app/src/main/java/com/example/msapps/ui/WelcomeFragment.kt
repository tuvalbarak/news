package com.example.msapps.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msapps.R
import com.example.msapps.models.Category
import com.example.msapps.ui.adapters.CategoriesAdapter
import kotlinx.android.synthetic.main.fragment_welcome.*


class WelcomeFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_welcome
    override val logTag = "WelcomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        //Divider between items
        fragment_welcome_rv_category.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        //Bind the RecyclerView with the adapter
//        fragment_welcome_rv_category.adapter = CategoryAdapter()
        val list: List<Category> = listOf(Category(1,"first"),
            Category(2,"second"),
            Category(3,"third"),
            Category(4,"fourth"),
            Category(5,"fifth"))

        val onCategoryClicked: (category: Category) -> Unit = {}

        fragment_welcome_rv_category.adapter = CategoriesAdapter(onCategoryClicked)
        (fragment_welcome_rv_category.adapter as CategoriesAdapter).submitList(list)

//        val flowerList = resources.getStringArray(R.array.flower_array).toMutableList()
//        val flowerAdapter = FlowerAdapter()
//        flowerAdapter.submitList(flowerList)

    }
}