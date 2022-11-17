package com.example.ecommerceconcept.utils

import com.example.ecommerceconcept.R

object Constants {
    fun getCategoriesList(): MutableList<Category> {
        return mutableListOf(
            Category("Phones", R.drawable.ic_phone, false),
            Category("Computer", R.drawable.ic_pc, false),
            Category("Health", R.drawable.ic_health, false),
            Category("Books", R.drawable.ic_books, false),
            Category("Tools", R.drawable.ic_tools, false)
        )
    }
}