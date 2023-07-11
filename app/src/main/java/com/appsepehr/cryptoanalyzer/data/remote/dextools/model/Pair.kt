package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class Pair(
    val _disclaimer: Disclaimer,
    val _id: String,
    val creationBlock: Int,
    val creationTime: String,
    val dextScore: DextScore,
    val fee: Double,
    val id: Id,
    val locks: List<Lock>,
    val metrics: Metrics,
    val name: String,
    val nameRef: String,
    val symbol: String,
    val symbolRef: String,
    val team: Team,
    val type: String,
    val votes: Votes
)