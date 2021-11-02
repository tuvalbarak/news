package com.example.msapps.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msapps.R
import com.example.msapps.models.Category
import com.example.msapps.ui.adapters.CategoriesAdapter
import com.example.msapps.ui.extensions.gone
import com.example.msapps.ui.extensions.show
import com.example.msapps.viewmodels.CategoryViewModel
import com.example.msapps.viewmodels.States
import com.example.msapps.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_category.*


class CategoryFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_category
    override val logTag = "CategoryFragment"

    private val categoryViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(CategoryViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupState()
        setupCategoriesList()
    }

    private fun setupRecyclerView() {
        //Divider between items
        fragment_category_rv_category.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        val onCategoryClicked: (category: Category) -> Unit = {
            Log.d(logTag, it.category)
            view?.findNavController()?.navigate(R.id.nav_dest_article_fragment) //Do I use Parcelize to send data to do I use VM?
        }

        fragment_category_rv_category.adapter = CategoriesAdapter(onCategoryClicked)

    }



    private fun setupState() {

        categoryViewModel.state.observe(viewLifecycleOwner, Observer { state ->

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


    private fun setupCategoriesList() {
        categoryViewModel.categoriesList.observe(viewLifecycleOwner, Observer { categoriesList ->
            (fragment_category_rv_category.adapter as CategoriesAdapter).submitList(categoriesList)
        })
    }

}