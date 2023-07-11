package com.appsepehr.cryptoanalyzer.data.remote.coinmarketcap

import com.appsepehr.cryptoanalyzer.data.remote.coinmarketcap.model.CoinMarketCapSpotLight
import com.appsepehr.cryptoanalyzer.data.remote.dextools.model.DexToolsGainer
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CoinMarketCapApi {
    @GET("cryptocurrency/spotlight")
    suspend fun getGainersAndLosers(
        @Query(value = "dataType") dataType: Int = 2,
        @Query(value = "limit") limit: Int = 15,
        @Query(value = "rankRange") rankRange: Int = 100,
        @Query(value = "timeframe") timeframe: String = "24h"
    ): CoinMarketCapSpotLight


    @GET("cryptocurrency/spotlight")
    suspend fun getSpotLight(
        @Query(value = "dataType") dataType: Int = 7,
        @Query(value = "limit") limit: Int = 10,
        @Query(value = "rankRange") rankRange: Int = 100,
        @Query(value = "timeframe") timeframe: String = "24h"
    ): CoinMarketCapSpotLight
}