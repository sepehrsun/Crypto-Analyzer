package com.appsepehr.cryptoanalyzer.data.remote.dextools.model

data class Audit(
    val codeVerified: Boolean,
    val date: String,
    val is_contract_renounced: Boolean,
    val lockTransactions: Boolean,
    val mint: Boolean,
    val provider: String,
    val proxy: Boolean,
    val status: String,
    val unlimitedFees: Boolean,
    val version: Int
)