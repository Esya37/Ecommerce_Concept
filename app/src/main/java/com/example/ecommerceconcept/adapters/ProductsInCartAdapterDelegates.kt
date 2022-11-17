package com.example.ecommerceconcept.adapters

import android.widget.ImageView
import android.widget.TextView
import com.example.domain.entities.BasketItem
import com.example.ecommerceconcept.R
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

fun productsInCartAdapterDelegates(decimalFormat: DecimalFormat, items: List<BasketItem>) =
    adapterDelegate<BasketItem, BasketItem>(R.layout.item_products_in_cart) {

        val productImageImageView: ImageView = findViewById(R.id.iv_product_in_cart_product_image)
        val productNameTextView: TextView = findViewById(R.id.tv_products_in_cart_product_name)
        val productCostTextView: TextView = findViewById(R.id.tv_products_in_cart_product_cost)
        val productCountTextView: TextView = findViewById(R.id.tv_product_counter_product_count)

        bind {
            Picasso.get()
                .load(item.imageSourceUrl)
                .into(productImageImageView)

            productNameTextView.text = item.title
            productCostTextView.text = decimalFormat.format(item.price)
            productCountTextView.text = items.count { product -> product == item }.toString()
        }

    }