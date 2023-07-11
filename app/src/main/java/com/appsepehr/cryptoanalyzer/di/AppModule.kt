package com.appsepehr.cryptoanalyzer.di

import com.appsepehr.cryptoanalyzer.data.remote.coingecko.CoinGeckoApi
import com.appsepehr.cryptoanalyzer.data.remote.coinmarketcap.CoinMarketCapApi
import com.appsepehr.cryptoanalyzer.data.remote.dextools.DexToolsApi
import com.appsepehr.cryptoanalyzer.utils.Constants.COIN_MARKET_CAP_BASE_URL
import com.appsepehr.cryptoanalyzer.utils.Constants.DEX_TOOLS_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoinMarketCapApi(): CoinMarketCapApi {
        return Retrofit.Builder()
            .baseUrl(COIN_MARKET_CAP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinMarketCapApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCoinGeckoApi():CoinGeckoApi{
        return CoinGeckoApi()
    }
    @Singleton
    @Provides
    fun provideDexToolsApi(): DexToolsApi {
        return Retrofit.Builder()
            .baseUrl(DEX_TOOLS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DexToolsApi::class.java)
    }
}