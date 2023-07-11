package com.appsepehr.cryptoanalyzer.data.remote.coingecko.model

data class CoinGeckoCrypto(
    var id: Int? = null,
    var name: String? = null,
    var slug: String? = null,
    var image: String? = null,
    var symbol: String? = null,
    var priceInBtc: Double? = null,
    var coinRank: Int? = null,
    var url: String? = null,
    var price: Double? = null,
    var liquidityBtc: Double? = null,
    var liquidity: Double? = null,
    var change1h: Double? = null,
    var change24h: Double? = null,
    var contractAddress: String? = null,
    var chain: String? = null,
    var fdvInBtc: Double? = null,
    var fdv: Double? = null,
    var lastAdded: String? = null,
)