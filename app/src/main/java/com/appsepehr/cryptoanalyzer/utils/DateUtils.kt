package com.appsepehr.cryptoanalyzer.utils

import java.text.SimpleDateFormat

fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy-HH:mm:ss")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}