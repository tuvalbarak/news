package com.example.msapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.msapps.Initializer
import com.example.msapps.R

/**
 * Implementing the app as a Single-Activity app, which means MainActivity will be the only Activity throughout the app.
 * Keeping MainActivity as thin as I can.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //calling init function that will perform initial actions for running the app.
        Initializer.init(application)
        setContentView(R.layout.activity_main)
    }
}