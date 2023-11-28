package com.elahe.livecurrencyrates.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.elahe.livecurrencyrates.data.Indicator
import com.elahe.livecurrencyrates.data.model.RateModel

class RateDiffCallback(private val oldList: List<RateModel>, private val newList: List<RateModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].symbol == newList[newItemPosition].symbol
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        if (oldItem.price != newItem.price) {
            if (newItem.price >= oldItem.price) {
                newItem.priceColor = Indicator.GREEN
            } else {
                newItem.priceColor = Indicator.RED
            }
        }
        return true
    }
}