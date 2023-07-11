package com.appsepehr.cryptoanalyzer.data.remote.coingecko.parser

import com.appsepehr.cryptoanalyzer.data.remote.coingecko.model.CoinGeckoCrypto
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.model.CoinGeckoGainerAndLoser
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class GainerLoserCoinGeckoParser {
    private val TAG = "CoinGeckoParserUtils"

    fun parseGainersLosersDocument(doc: Document): CoinGeckoGainerAndLoser {
        val coinGeckoGainerAndLoser = CoinGeckoGainerAndLoser(listOf(), listOf())

        val tables: Elements = doc.getElementsByClass("coingecko-table")

        tables.forEachIndexed { index, table ->
            if (index == 0)
                coinGeckoGainerAndLoser.gainer = parseTable(table)
            else if (index == 1)
                coinGeckoGainerAndLoser.loser = parseTable(table)
        }

//        Log.d(TAG, "gainerList: ${gainerAndLoser.gainer}")
//        Log.d(TAG, "loserList: ${gainerAndLoser.loser}")

        return coinGeckoGainerAndLoser
    }

    fun parseNewCoinDocument(doc: Document): CoinGeckoGainerAndLoser {
        val coinGeckoGainerAndLoser = CoinGeckoGainerAndLoser(listOf(), listOf())

        val tables: Elements = doc.getElementsByClass("coingecko-table")

        tables.forEachIndexed { index, table ->
            if (index == 0)
                coinGeckoGainerAndLoser.gainer = parseTable(table)
            else if (index == 1)
                coinGeckoGainerAndLoser.loser = parseTable(table)
        }

//        Log.d(TAG, "gainerList: ${gainerAndLoser.gainer}")
//        Log.d(TAG, "loserList: ${gainerAndLoser.loser}")

        return coinGeckoGainerAndLoser
    }
    private fun parseTable(table: Element): List<CoinGeckoCrypto> {
        val tableRows = table.getElementsByTag("tr")
        val cryptoList = mutableListOf<CoinGeckoCrypto>()

        tableRows.forEachIndexed { index, row ->
            if (index > 0) cryptoList.add(parseRow(row))
        }

        return cryptoList
    }

    private fun parseRow(row: Element): CoinGeckoCrypto {
        val cells = row.getElementsByTag("td")
        val crypto = CoinGeckoCrypto()

        cells.forEachIndexed { index, tdElement ->
            when (index) {
                0 -> parseFavoriteTd(tdElement, crypto)
                1 -> parseNumberTd(tdElement, crypto)
                2 -> parseCoinNameTd(tdElement, crypto)
                3 -> parsePriceTd(tdElement, crypto)
                4 -> parseLiquidityScoreTd(tdElement, crypto)
                5 -> parseStatPercentage(tdElement, crypto)
            }
//        Log.d(TAG, "cell${index}: " + tdElement.toString())
//        Log.d(TAG, "cell ${index}text: " + tdElement.text())
        }
//    Log.d(TAG, "crypto: ${crypto}")
        return crypto
    }

    private fun parseStatPercentage(element: Element?, crypto: CoinGeckoCrypto) {
        val statPercentage = element?.text()

        crypto.change24h = statPercentage?.replace(oldValue = "%", newValue = "")?.toDouble()

//    Log.d(TAG, "statPercentage = ${statPercentage}")
    }

    private fun parseLiquidityScoreTd(element: Element?, crypto: CoinGeckoCrypto) {
        val liquidityBtc = element?.attr("data-sort")
        val liquidityText = element?.text()
        val liquidity = liquidityText?.replace("$", "")?.replace(",", "")


        if (liquidity != null) crypto.liquidity = liquidity.toDouble()
        if (liquidityBtc != null) crypto.liquidityBtc = liquidityBtc.toDouble()

//    Log.d(TAG, "liquidityBtc = ${liquidityBtc}  liquidity = ${liquidity}")
    }

    private fun parsePriceTd(element: Element?, crypto: CoinGeckoCrypto) {
        val price = element?.attr("data-sort")

        if (price != null) crypto.price = price.toDouble()

//    Log.d(TAG, "price = ${price}")
    }

    private fun parseCoinNameTd(element: Element?, crypto: CoinGeckoCrypto) {
        val aTag = element?.getElementsByTag("a")

        val name = element?.attr("data-sort")
        val url = aTag?.attr("href")

        crypto.name = name
        crypto.url = "https://www.coingecko.com" + url

//    Log.d(TAG, "url = https://www.coingecko.com${url}")
//    Log.d(TAG, "name = ${name}")
    }

    private fun parseNumberTd(tdElement: Element?, crypto: CoinGeckoCrypto) {
        val coinRank = tdElement?.text()
        if (coinRank != null) crypto.coinRank = coinRank.toInt()

//    Log.d(TAG, "coinRank = ${coinRank}")
    }

    private fun parseFavoriteTd(element: Element?, crypto: CoinGeckoCrypto) {
        val iTag = element?.getElementsByTag("i")?.get(0)

        val id = iTag?.attr("data-coin-id")
        val slug = iTag?.attr("data-coin-slug")
        val image = iTag?.attr("data-coin-image")
        val symbol = iTag?.attr("data-coin-symbol")
        val priceInBtc = iTag?.attr("data-price-btc")

        if (id != null) crypto.id = id.toInt()
        if (slug != null) crypto.slug = slug
        if (image != null) crypto.image = image
        if (symbol != null) crypto.symbol = symbol
        if (priceInBtc != null) crypto.priceInBtc = priceInBtc.toDouble()

//    Log.d(TAG, "id = ${id} slug=${slug} image = ${image}  symbol=${symbol}  priceInBtc = ${priceInBtc} ")

    }
}