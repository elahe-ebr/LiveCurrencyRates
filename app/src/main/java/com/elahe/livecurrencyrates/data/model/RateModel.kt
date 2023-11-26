package com.elahe.livecurrencyrates.data.model

import com.elahe.livecurrencyrates.enums.Indicator


data class RateModel(
    var price: Double,
    val symbol: String,
    var priceColor: Indicator
)
