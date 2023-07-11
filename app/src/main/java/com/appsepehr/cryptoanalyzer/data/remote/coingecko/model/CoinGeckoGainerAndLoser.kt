package com.appsepehr.cryptoanalyzer.data.remote.coingecko.model

data class CoinGeckoGainerAndLoser(
    var gainer: List<CoinGeckoCrypto>,
    var loser: List<CoinGeckoCrypto>,
)
