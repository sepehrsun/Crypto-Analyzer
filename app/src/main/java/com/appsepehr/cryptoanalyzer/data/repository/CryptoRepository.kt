package com.appsepehr.cryptoanalyzer.data.repository

import android.util.Log
import com.appsepehr.cryptoanalyzer.data.model.Crypto
import com.appsepehr.cryptoanalyzer.data.model.Resource
import com.appsepehr.cryptoanalyzer.data.model.toExternal
import com.appsepehr.cryptoanalyzer.data.remote.coingecko.CoinGeckoApi
import com.appsepehr.cryptoanalyzer.data.remote.coinmarketcap.CoinMarketCapApi
import com.appsepehr.cryptoanalyzer.data.remote.dextools.DexToolsApi
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val dexToolsApi: DexToolsApi,
    private val coinGeckoApi: CoinGeckoApi,
    private val coinMarketCapApi: CoinMarketCapApi
) {

    suspend fun getGainerDexTools(interval: String): Resource<List<Crypto>> {
        return try {
            Resource.Loading(data = true)
            val gainersList = dexToolsApi.getGainers(interval = interval).data.toExternal()
            if (gainersList.isNotEmpty()) Resource.Loading(data = false)
            Log.d("TAG", "getGainer${interval}: ${gainersList.get(0)}")
            Resource.Success(data = gainersList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }
    }
    suspend fun getGainerCoinGecko(interval: String): Resource<List<Crypto>> {
        return try {
            Resource.Loading(data = true)
            val gainerList = coinGeckoApi.getGainerAndLoser(interval).gainer.toExternal()
            if (gainerList.isNotEmpty()) Resource.Loading(data = false)
            Log.d("TAG", "getGainer${interval}: ${gainerList.get(0)}")
            Resource.Success(data = gainerList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }
    }
    private suspend fun getLoserCoinGecko(interval: String): Resource<List<Crypto>> {
        return try {
            Resource.Loading(data = true)
            val gainerList = coinGeckoApi.getGainerAndLoser(interval).loser.toExternal()
            if (gainerList.isNotEmpty()) Resource.Loading(data = false)
            Log.d("TAG", "getGainer${interval}: ${gainerList.get(0)}")
            Resource.Success(data = gainerList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }
    }

    suspend fun getNewCoinsCoinGecko(): Resource<List<Crypto>> {
        return try {
            Resource.Loading(data = true)
            val cryptoList = coinGeckoApi.getNewCoins().toExternal()
            if (cryptoList.isNotEmpty()) Resource.Loading(data = false)
            Log.d("TAG", "NewCoins: ${cryptoList.get(0)}")
            Resource.Success(data = cryptoList)
        } catch (exception: Exception) {
            exception.printStackTrace()
            Resource.Error(message = exception.message.toString())
        }
    }

    suspend fun getGainerCoinMarketCap(timeframe: String): Resource<List<Crypto>> {
        return try {
            Resource.Loading(data = true)
            val cryptoList = coinMarketCapApi.getGainersAndLosers(timeframe = timeframe).data.gainerList.toExternal()
            if (cryptoList.isNotEmpty()) Resource.Loading(data = false)
            Log.d("TAG", "getGainer${timeframe}: ${cryptoList.get(0)}")
            Resource.Success(data = cryptoList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }
    }



}