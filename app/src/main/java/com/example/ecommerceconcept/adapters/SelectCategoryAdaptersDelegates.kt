package com.example.ecommerceconcept.adapters


import android.content.res.Resources
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.utils.Category
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun selectedCategoryAdapterDelegate(resources: Resources) =
    adapterDelegate<Category, Category>(R.layout.item_select_category_selected,
        on = { item: Category, _: List<Category>, _: Int ->
            return@adapterDelegate item.isSelected
        }) {

        val name: TextView = findViewById(R.id.tv_select_category_name)
        val icon: ImageView = findViewById(R.id.iv_select_category_icon)

        bind {
            name.text = item.name
            name.width = (resources.displayMetrics.widthPixels * 0.23).toInt()
            icon.setImageDrawable(ResourcesCompat.getDrawable(resources, item.iconId, null))
        }
    }

fun unselectedCategoryAdapterDelegate(
    resources: Resources, itemClickedListener: (Category) -> Unit
) = adapterDelegate<Category, Category>(R.layout.item_select_category_unselected,
    on = { item: Category, _: List<Category>, _: Int ->
        return@adapterDelegate !item.isSelected
    }) {

    val name: TextView = findViewById(R.id.tv_select_category_name)
    val icon: ImageView = findViewById(R.id.iv_select_category_icon)
    icon.setOnClickListener { itemClickedListener(item) }

    bind {
        name.text = item.name
        name.width = (resources.displayMetrics.widthPixels * 0.23).toInt()
        icon.setImageDrawable(ResourcesCompat.getDrawable(resources, item.iconId, null))
    }
}