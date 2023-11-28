package com.elahe.livecurrencyrates.data

import com.elahe.livecurrencyrates.R

enum class Indicator(val color: Int, val icon:Int) {
    GREEN(R.color.green, R.drawable.ic_arrow_up), RED(R.color.red, R.drawable.ic_arrow_down)
}

enum class Symbol(val image: Int) {
    EURUSD(R.drawable.eurusd),
    GBPJPY(R.drawable.gbpjpy),
    AUDCAD(R.drawable.audcad),
    JPYAED(R.drawable.jpyaed),
    JPYSEK(R.drawable.jpysek),
    USDGBP(R.drawable.usdgbp),
    USDCAD(R.drawable.usdcad);

    companion object {
        fun getSymbolImage(symbol: String): Int {
            return Symbol.valueOf(symbol).image
        }
    }
}

