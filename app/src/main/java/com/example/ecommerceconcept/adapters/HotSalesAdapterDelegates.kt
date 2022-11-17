package com.example.ecommerceconcept.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.domain.entities.HomeStore
import com.example.ecommerceconcept.R
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.squareup.picasso.Picasso

fun hotSalesAdapterDelegates() =
    adapterDelegate<HomeStore, HomeStore>(R.layout.item_hot_sales) {

        val hotSalesImageView: ImageView = findViewById(R.id.iv_hot_sales)
        val hotSalesTitleTextView: TextView = findViewById(R.id.tv_title_hot_sales)
        val hotSalesSubtitleTextView: TextView = findViewById(R.id.tv_subtitle_hot_sales)
        val hotSalesNewMark: View = findViewById(R.id.include_new_mark)

        bind {
            Picasso.get()
                .load(item.imageSourceUrl)
                .into(hotSalesImageView)

            hotSalesTitleTextView.text = item.title
            hotSalesSubtitleTextView.text = item.subtitle
            if (item.isNew) {
                hotSalesNewMark.visibility = View.VISIBLE
            } else {
                hotSalesNewMark.visibility = View.INVISIBLE
            }
        }
    }