package com.appsepehr.cryptoanalyzer.data.model

import com.appsepehr.cryptoanalyzer.data.remote.coingecko.model.CoinGeckoCrypto
import com.appsepehr.cryptoanalyzer.data.remote.coinmarketcap.model.CryptoData
import com.appsepehr.cryptoanalyzer.data.remote.dextools.model.Data

/*
fun Crypto.toLocal = LocalCrypto(
    name = name,
    chain = chain
)*/

fun Data.toExternal() = Crypto(
    name = token.name,
    chain = _id.chain,
    token = _id.token,
    volume = volume,
    price = price,
    priceDiff = priceDiff,
    logoLink = "https://www.dextools.io/resources/tokens/logos/" + token.logo,
    creationTime = token.creationTime,
    score = pair.dextScore.total,
    link = "https://www.dextools.io/app/en/" + _id.chain + "/pair-explorer/" + _id.pair
)

fun CoinGeckoCrypto.toExternal() = Crypto(
    name = name,
    chain = null,
    token = null,
    volume = liquidity,
    price = price,
    priceDiff = change24h,
    logoLink = image,
    creationTime =null ,
    score = null,
    link = url
)
fun CryptoData.toExternal() = Crypto(
    name = name,
    chain = null,
    token = null,
    volume = priceChange.volume24h,
    price = priceChange.price,
    priceDiff = priceChange.priceChange24h,
    logoLink = "https://s2.coinmarketcap.com/static/img/coins/64x64/"+id+".png",
    creationTime =null ,
    score = null,
    link = "https://coinmarketcap.com/currencies/${slug}/"
)

@JvmName("dexToolToExternal")
fun List<Data>.toExternal() = mapNotNull(Data::toExternal)

@JvmName("coinGeckoToExternal")
fun List<CoinGeckoCrypto>.toExternal() = mapNotNull(CoinGeckoCrypto::toExternal)

@JvmName("coinMarketCapToExternal")
fun List<CryptoData>.toExternal() = mapNotNull(CryptoData::toExternal)