package com.appsepehr.cryptoanalyzer.utils

fun formatPrice(price: Double): String {
    return "$" + "%.4f".format(price)
}

fun formatPriceDiff(priceDiff: Double?): String {
    return "%" + "%.0f".format(priceDiff)
}

fun formatVolume(volume: Double): String {
    if (volume > 1000000)
        return "%.2f".format(volume/1000000)+"M"
    else if(volume>1000)
        return "%.2f".format(volume/1000)+"K"
    else
        return "%.2f".format(volume)
}