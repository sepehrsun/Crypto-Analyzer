package com.appsepehr.cryptoanalyzer.data.remote.dexscreener.parser

import android.util.Log
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.model.CoinGeckoCrypto
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class GainerDexScreenerParser {

    fun parseGainerDocument(doc: Document) {
        var newCoin = listOf<CoinGeckoCrypto>()

        val tables: Elements = doc.getElementsByClass("ds-dex-table")

        if (tables.size > 0)
            newCoin = parseTable(tables.get(0))

//        return newCoin
    }

    private fun parseTable(table: Element): List<CoinGeckoCrypto> {
        val tableRows = table.getElementsByClass("ds-dex-table-row")
        val cryptoList = mutableListOf<CoinGeckoCrypto>()

        tableRows.forEachIndexed { index, row ->
            cryptoList.add(parseRow(row))
        }

        return cryptoList
    }

    private fun parseRow(row: Element): CoinGeckoCrypto {
        val crypto = CoinGeckoCrypto()
        val href = row.attr("href")
        val url = "https://dexscreener.com" + href
        val splitHref = href.split("/")
        splitHref.forEachIndexed { index, s ->
            Log.d("TAG", "parseRow: ${index} = ${s}")
        }

        /*val cells = row.getElementsByClass("ds-table-data-cell")
        cells.forEachIndexed { index, cellElement ->
            when (index) {
                0 -> parseTokenCell(cellElement, crypto)
                1 -> parsePriceCell(cellElement, crypto)
                2 -> parseTransactionNumberCell(cellElement, crypto)
                3 -> parseVolumeCell(cellElement, crypto)
                4 -> parseMakersCell(cellElement, crypto)
                5 -> parsePriceChange5mCell(cellElement, crypto)
                6 -> parsePriceChange1hCell(cellElement, crypto)
                7 -> parsePriceChange6hCell(cellElement, crypto)
                8 -> parsePriceChange24hCell(cellElement, crypto)
                9 -> parseLiquidityCell(cellElement, crypto)
                10 -> parseFdvCell(cellElement, crypto)

            }
        }*/

        return crypto
    }
}