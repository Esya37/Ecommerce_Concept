package com.example.ecommerceconcept.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.example.domain.entities.BestSeller
import com.example.ecommerceconcept.R
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.squareup.picasso.Picasso

fun bestSellerAdapterDelegates(
    resources: Resources,
    onFavouriteClickListener: (BestSeller) -> Unit,
    onPhoneClickListener: () -> Unit
) =
    adapterDelegate<BestSeller, BestSeller>(R.layout.item_best_seller) {

        val bestSellerImageView: ImageView = findViewById(R.id.iv_best_seller_image)
        val bestSellerDiscountPriceTextView: TextView =
            findViewById(R.id.tv_best_seller_discount_price)
        val bestSellerPriceWithoutDiscountTextView: TextView =
            findViewById(R.id.tv_best_seller_price_without_discount)
        val bestSellerNameTextView: TextView = findViewById(R.id.tv_best_seller_name)
        val favouriteMarkConstraintLayout: ConstraintLayout = findViewById(R.id.csl_favorite_mark)

        itemView.setOnClickListener { onPhoneClickListener() }

        bind {
            bestSellerImageView.updateLayoutParams {
                height = (resources.displayMetrics.heightPixels * 0.19).toInt()
            }
            Picasso.get()
                .load(item.imageSourceUrl)
                .into(bestSellerImageView)

            bestSellerDiscountPriceTextView.text = String.format(
                resources.getString(R.string.cost_in_dollars),
                item.discountPrice.toString()
            )
            bestSellerPriceWithoutDiscountTextView.text = String.format(
                resources.getString(R.string.cost_in_dollars),
                item.priceWithoutDiscount.toString()
            )
            bestSellerNameTextView.text = item.title

            favouriteMarkConstraintLayout.removeAllViews()
            favouriteMarkConstraintLayout.addView(
                LayoutInflater.from(context).inflate(
                    if (item.isFavorites) {
                        R.layout.favorite_mark_selected
                    } else {
                        R.layout.favorite_mark_unselected
                    },
                    favouriteMarkConstraintLayout,
                    false
                )
            )

            favouriteMarkConstraintLayout
                .setOnClickListener {
                    onFavouriteClickListener(item)
                }
        }
    }