package com.example.msapps.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Centralizes all the common things among the Fragments throughout the app.
 */
abstract class BaseFragment : Fragment() {
    abstract val layoutRes: Int
    abstract val logTag: String

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutRes, container, false)

}