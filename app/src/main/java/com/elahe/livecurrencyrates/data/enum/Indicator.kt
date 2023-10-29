package com.elahe.livecurrencyrates.data.enum

import com.elahe.livecurrencyrates.R

enum class Indicator(val color: Int, val icon:Int) {
    GREEN(R.color.green,R.drawable.ic_arrow_up), RED(R.color.red,R.drawable.ic_arrow_down)
}