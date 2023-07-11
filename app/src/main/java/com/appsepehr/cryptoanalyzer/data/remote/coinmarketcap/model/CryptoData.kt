package com.appsepehr.cryptoanalyzer.data.remote.coinmarketcap.model

data class CryptoData(
    val dataType: Int,
    val id: Int,
    val marketCap: Double,
    val name: String,
    val priceChange: PriceChange,
    val rank: Int,
    val selfReportedMarketCap: Double,
    val slug: String,
    val status: String,
    val symbol: String
)