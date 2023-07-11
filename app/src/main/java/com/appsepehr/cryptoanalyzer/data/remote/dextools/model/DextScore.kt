package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class DextScore(
    val creation: Int,
    val holders: Int,
    val information: Int,
    val pool: Int,
    val total: Int,
    val transactions: Int
)