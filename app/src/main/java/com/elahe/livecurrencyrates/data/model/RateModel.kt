package com.elahe.livecurrencyrates.data.model

import com.elahe.livecurrencyrates.data.Indicator


data class RateModel(
    var price: Double,
    val symbol: String,
    var priceColor: Indicator
)
