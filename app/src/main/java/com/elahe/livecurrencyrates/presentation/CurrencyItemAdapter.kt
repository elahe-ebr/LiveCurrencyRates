package com.elahe.livecurrencyrates.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elahe.livecurrencyrates.data.enum.Symbol
import com.elahe.livecurrencyrates.data.model.RateModel
import com.elahe.livecurrencyrates.databinding.LayoutItemCurrenvyBinding
import java.util.ArrayList

class CurrencyItemAdapter : RecyclerView.Adapter<CurrencyItemAdapter.ViewHolder>() {

    var list: MutableList<RateModel> = ArrayList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: LayoutItemCurrenvyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rateModel: RateModel) {
            binding.tvSymbol.text = rateModel.symbol.chunked(3).joinToString("/")
            binding.tvPrice.text = String.format("%.4f", rateModel.price)
            binding.ivIcon.setImageDrawable(
                binding.root.resources.getDrawable(
                    Symbol.getSymbolImage(
                        rateModel.symbol
                    )
                )
            )
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
}