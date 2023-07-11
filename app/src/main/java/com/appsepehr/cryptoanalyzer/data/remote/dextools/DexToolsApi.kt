package com.appsepehr.cryptoanalyzer.data.remote.dextools

import com.appsepehr.cryptoanalyzer.data.remote.dextools.model.DexToolsGainer
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface DexToolsApi {
    @GET("analytics/pairs/gainers")
    suspend fun getGainers(
        @Query(value = "limit") limit: Int = 10,
        @Query(value = "interval") interval: String,
        @Query(value = "page") page: Int = 1
    ):DexToolsGainer

    @GET("hotpairs/hot")
    suspend fun getAllHotPairs()

    @GET("hotpairs/hot")
    suspend fun getHotPairs(@Query(value = "chain") chain: String)

}