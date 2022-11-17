package com.example.ecommerceconcept.adapters

import android.widget.TextView
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.utils.PhoneSd
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun phoneSdsSelectedAdapterDelegates() =
    adapterDelegate<PhoneSd, PhoneSd>(R.layout.item_phone_sds_selected,
        on = { item: PhoneSd, _: List<PhoneSd>, _: Int ->
            return@adapterDelegate item.isSelected
        }) {

        val ssdCapacityTextView: TextView = findViewById(R.id.tv_phone_ssds_capacity)

        bind {
            ssdCapacityTextView.text = String.format(
                getString(R.string.capacity_in_gb),
                item.sdCapacity
            )
        }
    }

fun phoneSdsUnselectedAdapterDelegates(onClickListener: (PhoneSd) -> Unit) =
    adapterDelegate<PhoneSd, PhoneSd>(R.layout.item_phone_sds_unselected,
        on = { item: PhoneSd, _: List<PhoneSd>, _: Int ->
            return@adapterDelegate !item.isSelected
        }) {

        val ssdCapacityTextView: TextView = findViewById(R.id.tv_phone_ssds_capacity)

        itemView.setOnClickListener { onClickListener(item) }

        bind {
            ssdCapacityTextView.text = String.format(
                getString(R.string.capacity_in_gb),
                item.sdCapacity
            )
        }
    }