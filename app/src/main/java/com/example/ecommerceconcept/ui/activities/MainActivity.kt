package com.example.ecommerceconcept.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecommerceconcept.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_EcommerceConcept)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}