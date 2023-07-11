package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class Id(
    val chain: String,
    val exchange: String,
    val pair: String,
    val token: String,
    val tokenRef: String
)