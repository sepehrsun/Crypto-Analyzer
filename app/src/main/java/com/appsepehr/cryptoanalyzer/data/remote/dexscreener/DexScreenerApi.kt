package com.appsepehr.cryptoanalyzer.data.remote.dexscreener

import android.util.Log
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.model.CoinGeckoCrypto
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.parser.NewCoinCoinGeckoParser
import com.appsepehr.cryptoanalyzer.data.remote.dexscreener.parser.GainerDexScreenerParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class DexScreenerApi {

    suspend fun getGainers(): List<CoinGeckoCrypto> {
        val parser = GainerDexScreenerParser()
//        val newCoinList: List<CoinGeckoCrypto>

        try {
            val doc: Document = Jsoup
                .connect("https://dexscreener.com/gainers")
                .userAgent("Mozilla")
                .timeout(25000)
                .get()

            parser.parseGainerDocument(doc)
        } catch (e: IOException) {
            Log.d("error", "error: ${e.message}")
            e.printStackTrace()

            throw e
        }

        return listOf()
    }

}