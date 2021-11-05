package com.example.msapps.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.msapps.R
import com.example.msapps.models.Category
import com.example.msapps.ui.adapters.CategoriesAdapter
import com.example.msapps.ui.extensions.gone
import com.example.msapps.ui.extensions.show
import com.example.msapps.utils.States
import com.example.msapps.viewmodels.CategoryViewModel
import com.example.msapps.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_category.*


class CategoryFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_category
    override val logTag = "CategoryFragment"

    private val categoryViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(CategoryViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //FAB click listener
        fragment_category_fab_favorites.setOnClickListener {
            //Using SafeArgs to tell ArticleFragment that it needs to display favorites.
            view.findNavController().navigate(R.id.nav_dest_article_fragment)
        }
        setupRecyclerView()
        setupState()
        setupCategoriesList()
    }

    private fun setupRecyclerView() {
        //Using NavGraph to navigate between Fragments. Sending it to the adapter and it will be invoked after every click on a category.
        val onCategoryClicked: (category: Category) -> Unit = {
            Log.d(logTag, it.toString())
            view?.findNavController()?.navigate(
                    CategoryFragmentDirections.navActionCategoryFragment(it.toString())
            )
        }
        //Binding adapter and recyclerview.
        fragment_category_rv_category.adapter = CategoriesAdapter(onCategoryClicked)
    }

    /**
     * Observing the current app state.
     * Using View extension functions (cleaner and easier to read) to change the visibility of the progress bar.
     */
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
                States.DeletedFromFavorites -> {
                    Log.d(logTag, "DeletedFromFavorites")
                    fragment_category_pb_progress_bar.gone()
                }
            }
        })
    }

    /**
     * Observing categoriesList, which holds the current list that needs to be displayed.
     * Using submitList to achieve a better performance while changes occur.
     */
    private fun setupCategoriesList() {
        categoryViewModel.categoriesList.observe(viewLifecycleOwner, Observer { categoriesList ->
            (fragment_category_rv_category.adapter as CategoriesAdapter).submitList(categoriesList)
        })
    }

}