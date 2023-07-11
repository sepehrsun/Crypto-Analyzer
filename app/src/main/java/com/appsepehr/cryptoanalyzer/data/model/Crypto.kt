package com.appsepehr.cryptoanalyzer.data.model

data class Crypto(
    val name: String?,
    val chain: String?,
    val token: String?,
    val volume: Double?,
    val price: Double? ,
    val priceDiff: Double?,
    val logoLink: String? ,
    val creationTime: String? ,
    val score: Int? ,
    val link : String?
)
