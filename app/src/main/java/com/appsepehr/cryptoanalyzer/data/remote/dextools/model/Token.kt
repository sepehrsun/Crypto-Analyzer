package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class Token(
    val audit: Audit,
    val creationBlock: Int,
    val creationTime: String,
    val decimals: Int,
    val info: Info,
    val links: Links,
    val locks: List<Lock>,
    val logo: String,
    val metrics: MetricsX,
    val name: String,
    val reprPair: ReprPair,
    val symbol: String,
    val totalSupply: String
)