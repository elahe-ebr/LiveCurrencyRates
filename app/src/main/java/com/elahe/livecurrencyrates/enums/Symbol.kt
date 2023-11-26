package com.elahe.livecurrencyrates.enums

import com.elahe.livecurrencyrates.R

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

