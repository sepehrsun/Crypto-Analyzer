package com.appsepehr.cryptoanalyzer.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun Context.copyToClipboard(text: CharSequence){
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("contract",text)
    clipboard.setPrimaryClip(clip)
}