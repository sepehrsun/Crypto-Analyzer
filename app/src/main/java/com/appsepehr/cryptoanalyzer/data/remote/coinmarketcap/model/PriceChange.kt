package com.appsepehr.cryptoanalyzer.data.remote.coinmarketcap.model

data class PriceChange(
    val lastUpdate: String,
    val price: Double,
    val priceChange1h: Double?,
    val priceChange24h: Double,
    val priceChange30d: Double,
    val priceChange7d: Double,
    val volume24h: Double
)