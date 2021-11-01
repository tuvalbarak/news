package com.example.msapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.msapps.Initializer
import com.example.msapps.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Initializer.init(application)
        setContentView(R.layout.activity_main)
    }
}