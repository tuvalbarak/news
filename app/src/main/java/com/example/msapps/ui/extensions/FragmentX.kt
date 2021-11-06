package com.example.msapps.ui.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Creating this function as an extension function lets me the possibility to use it from anywhere across the app (in a real app - I would use some custom UI
 * to fit it to the app theme.
 */
fun Fragment.displaySnackbar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}