package com.elahe.livecurrencyrates.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elahe.livecurrencyrates.data.Indicator
import com.elahe.livecurrencyrates.data.Symbol.Companion.getSymbolImage
import com.elahe.livecurrencyrates.data.model.RateModel
import com.elahe.livecurrencyrates.databinding.LayoutItemCurrenvyBinding


class CurrencyItemAdapter : RecyclerView.Adapter<CurrencyItemAdapter.ViewHolder>() {

    private var list: MutableList<RateModel> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: LayoutItemCurrenvyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rateModel: RateModel) {
            val context = binding.root.context
            binding.tvSymbol.text = rateModel.symbol.chunked(3).joinToString("/")
            binding.tvPrice.text = String.format("%.4f", rateModel.price)
            binding.ivIcon.setImageResource(getSymbolImage(rateModel.symbol))
            if (rateModel.priceColor == Indicator.GREEN) {
                binding.ivIndicator.setImageResource(Indicator.GREEN.icon)
                binding.ivIndicator.setColorFilter(context.getColor(Indicator.GREEN.color))
            } else if (rateModel.priceColor == Indicator.RED) {
                binding.ivIndicator.setImageResource(Indicator.RED.icon)
                binding.ivIndicator.setColorFilter(context.getColor(Indicator.RED.color))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemCurrenvyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateRateListItems(rates: List<RateModel>) {
        val diffCallback = RateDiffCallback(this.list, rates)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.list = rates.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }
}