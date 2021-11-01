package com.example.msapps.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msapps.R
import kotlinx.android.synthetic.main.fragment_welcome.*


class WelcomeFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_welcome
    override val logTag = "WelcomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        //Divider between items
        fragment_welcome_rv_category.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        //Bind the RecyclerView with the adapter
        fragment_welcome_rv_category.adapter = CategoryAdapter()
    }
}