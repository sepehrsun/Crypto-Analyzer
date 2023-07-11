package com.appsepehr.cryptoanalyzer.data.model

data class Response<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)