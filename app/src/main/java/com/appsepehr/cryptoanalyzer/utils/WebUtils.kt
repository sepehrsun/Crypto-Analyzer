package com.appsepehr.cryptoanalyzer.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import kotlin.math.log

fun openUrl(context: Context, url: String?) {
    if (url != null) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        context.startActivity(openURL)
    }
}