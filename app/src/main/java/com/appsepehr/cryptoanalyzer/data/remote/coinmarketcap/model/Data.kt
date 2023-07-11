package com.appsepehr.cryptoanalyzer.data.remote.coinmarketcap.model

data class Data(
    val trendingList: List<CryptoData>,
    val gainerList: List<CryptoData>,
    val loserList: List<CryptoData>,
    val mostVisitedList: List<CryptoData>
)