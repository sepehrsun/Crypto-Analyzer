package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class Metrics(
    val initialLiquidity: Double,
    val liquidity: Double,
    val liquidityUpdatedAt: String,
    val reserve: Double,
    val reserveRef: Double
)