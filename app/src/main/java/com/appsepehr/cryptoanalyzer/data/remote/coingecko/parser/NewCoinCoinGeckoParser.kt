package com.appsepehr.cryptoanalyzer.data.remote.coingecko.parser

import androidx.core.text.isDigitsOnly
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.model.CoinGeckoCrypto
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class NewCoinCoinGeckoParser {
    private val TAG = "CoinGeckoParserUtils"

    fun parseNewCoinDocument(doc: Document): List<CoinGeckoCrypto> {
        var newCoin = listOf<CoinGeckoCrypto>()

        val tables: Elements = doc.getElementsByClass("coingecko-table")
        if (tables.size > 0) newCoin = parseTable(tables.get(0))

        return newCoin
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
                4 -> parseChainTd(tdElement, crypto)
                5 -> parseChange1hTd(tdElement, crypto)
                6 -> parseChange24hTd(tdElement, crypto)
                7 -> parseLiquidityScoreTd(tdElement, crypto)
                8 -> parseFdvTd(tdElement, crypto)
                9 -> parseLastAddedTd(tdElement, crypto)

            }
//        Log.d(TAG, "cell${index}: " + tdElement.toString())
//        Log.d(TAG, "cell ${index}text: " + tdElement.text())
        }
//    Log.d(TAG, "crypto: ${crypto}")
        return crypto
    }

    private fun parseLastAddedTd(element: Element?, crypto: CoinGeckoCrypto) {
        crypto.lastAdded = element?.text()
    }

    private fun parseFdvTd(element: Element?, crypto: CoinGeckoCrypto) {
        val spanTag = element?.getElementsByTag("span")
        val fdvBtc = spanTag?.attr("data-price-btc")
        val fdv = spanTag?.attr("data-price-previous")

        if (!fdvBtc.isNullOrEmpty()) crypto.fdvInBtc = fdvBtc.toDouble()
        if (!fdv.isNullOrEmpty()) crypto.fdv = fdv.toDouble()
    }

    private fun parseLiquidityScoreTd(element: Element?, crypto: CoinGeckoCrypto) {
        val liquidityBtc = element?.attr("data-sort")
        val liquidityText = element?.text()
        val liquidity = liquidityText?.replace("$", "")?.replace(",", "")


        if (liquidity != null) crypto.liquidity = liquidity.toDouble()
        if (liquidityBtc != null) crypto.liquidityBtc = liquidityBtc.toDouble()

//    Log.d(TAG, "liquidityBtc = ${liquidityBtc}  liquidity = ${liquidity}")
    }

    private fun parseChange24hTd(element: Element?, crypto: CoinGeckoCrypto) {
        val change24h = element?.attr("data-sort")

        if (!change24h.isNullOrEmpty())
            try {
                crypto.change24h = change24h.toDouble()
            } catch (_: Exception) { }
    }

    private fun parseChange1hTd(element: Element?, crypto: CoinGeckoCrypto) {
        val change1h = element?.attr("data-sort")

        if (!change1h.isNullOrEmpty())
            try {
                crypto.change1h = change1h.toDouble()
            } catch (_: Exception) { }
    }

    private fun parseChainTd(element: Element?, crypto: CoinGeckoCrypto) {
        val aTag = element?.getElementsByTag("i")

        val chain = element?.attr("data-sort")
        val contractAddress = aTag?.attr("data-address")

        if (contractAddress != null) crypto.contractAddress = contractAddress
        if (chain != null) crypto.chain = chain

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

        if (!coinRank.isNullOrEmpty()) crypto.coinRank = coinRank.toInt()
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