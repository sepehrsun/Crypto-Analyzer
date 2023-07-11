package com.appsepehr.cryptoanalyzer.data.remote.coingecko

import android.util.Log
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.model.CoinGeckoCrypto
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.model.CoinGeckoGainerAndLoser
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.parser.GainerLoserCoinGeckoParser
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.parser.NewCoinCoinGeckoParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class CoinGeckoApi {

    suspend fun getGainerAndLoser(interval: String): CoinGeckoGainerAndLoser {
        val parser = GainerLoserCoinGeckoParser()
        val coinGeckoGainerAndLoser: CoinGeckoGainerAndLoser

        try {
            val doc: Document = Jsoup
                .connect("https://www.coingecko.com/en/crypto-gainers-losers")
                .data("time", interval)
                .userAgent("Mozilla")
                .timeout(20000)
                .get()

            coinGeckoGainerAndLoser = parser.parseGainersLosersDocument(doc)
        } catch (e: IOException) {
            Log.d("error", "error: ${e.message}")
            e.printStackTrace()

            throw e
        }

        return coinGeckoGainerAndLoser
    }

    suspend fun getNewCoins(): List<CoinGeckoCrypto> {
        val parser = NewCoinCoinGeckoParser()
        val newCoinList: List<CoinGeckoCrypto>

        try {
            val doc: Document = Jsoup
                .connect("https://www.coingecko.com/en/new-cryptocurrencies")
                .userAgent("Mozilla")
                .timeout(20000)
                .get()

            newCoinList = parser.parseNewCoinDocument(doc)
        } catch (e: IOException) {
            Log.d("error", "error: ${e.message}")
            e.printStackTrace()

            throw e
        }

        return newCoinList
    }


}