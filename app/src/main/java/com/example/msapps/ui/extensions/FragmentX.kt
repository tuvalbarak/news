package com.example.msapps.ui.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.displaySnackbar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}