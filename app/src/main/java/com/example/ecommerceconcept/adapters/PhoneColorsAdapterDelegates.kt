package com.example.ecommerceconcept.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.utils.PhoneColor
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun phoneColorsAdapterDelegates(onClickListener: (PhoneColor) -> Unit) =
    adapterDelegate<PhoneColor, PhoneColor>(R.layout.item_phone_colors) {

        val checkMarkImageView: ImageView = findViewById(R.id.iv_phone_colors_check_mark)
        val phoneColorImageView: ImageView = findViewById(R.id.iv_phone_colors_color)

        itemView.setOnClickListener { onClickListener(item) }

        bind {
            phoneColorImageView.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor(item.colorString))

            if (item.isSelected) {
                checkMarkImageView.visibility = View.VISIBLE
            } else {
                checkMarkImageView.visibility = View.INVISIBLE
            }
        }

    }