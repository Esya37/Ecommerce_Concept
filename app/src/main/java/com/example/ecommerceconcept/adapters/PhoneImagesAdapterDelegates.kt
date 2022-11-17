package com.example.ecommerceconcept.adapters

import android.widget.ImageView
import com.example.ecommerceconcept.R
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.squareup.picasso.Picasso

fun phoneImagesAdapterDelegates() = adapterDelegate<String, String>(R.layout.item_phone_images) {

    val phoneImageImageView: ImageView = findViewById(R.id.iv_phone_images_phone_image)

    bind {
        Picasso.get()
            .load(item)
            .into(phoneImageImageView)
    }

}