package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class MetricsX(
    val circulatingSupply: Double,
    val fdv: Double,
    val holders: Int,
    val maxSupply: Double,
    val totalSupply: Double,
    val txCount: Int
)