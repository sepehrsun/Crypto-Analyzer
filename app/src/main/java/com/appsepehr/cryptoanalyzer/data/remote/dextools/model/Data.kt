package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class Data(
    val _id: Id,
    val pair: Pair,
    val price: Double,
    val priceDiff: Double,
    val priceInterval: Double,
    val swaps: Int,
    val token: Token,
    val volume: Double
)