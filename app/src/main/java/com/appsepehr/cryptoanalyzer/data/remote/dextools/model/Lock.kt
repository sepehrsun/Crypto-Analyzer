package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class Lock(
    val _id: String,
    val amount: Double,
    val api: String,
    val lockId: String,
    val percent: Double,
    val providerId: String,
    val type: String,
    val unlockDate: String
)