package com.appsepehr.cryptoanalyzer.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsepehr.cryptoanalyzer.data.model.Crypto
import com.appsepehr.cryptoanalyzer.data.model.Resource
import com.appsepehr.cryptoanalyzer.data.remote.CryptoAnalyzerApis
import com.appsepehr.cryptoanalyzer.data.remote.dexscreener.DexScreenerApi
import com.appsepehr.cryptoanalyzer.data.repository.CryptoRepository
import com.appsepehr.cryptoanalyzer.utils.exportToExcelFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) :
    ViewModel() {

    var cryptoList: List<Crypto> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        load("24h", CryptoAnalyzerApis.DexTools.name)

//        viewModelScope.launch(Dispatchers.Default) {
//            DexScreenerApi().getGainers()
//        }
    }

    fun loadNewCoinsCoinGecko() {
        isLoading = true
        viewModelScope.launch(Dispatchers.Default) {

            try {
                when (val response = cryptoRepository.getNewCoinsCoinGecko()) {
                    is Resource.Success -> {
                        cryptoList = response.data!!
                        if (cryptoList.isNotEmpty()) isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "getNewCoinsCoinGecko: Failed getting New coins")
                    }
                    else -> {
                        isLoading = false
                    }
                }

            } catch (exception: Exception) {
                isLoading = false
                Log.d("Network", "getNewCoinsCoinGecko: ${exception.message.toString()}")
            }
        }

    }

    fun loadGainerCoinGecko(timeFrame: String) {
        isLoading = true
        viewModelScope.launch(Dispatchers.Default) {

            try {
                when (val response = cryptoRepository.getGainerCoinGecko(timeFrame)) {
                    is Resource.Success -> {
                        cryptoList = response.data!!
                        if (cryptoList.isNotEmpty()) isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "getGainer${timeFrame}CoinGecko: Failed getting Gainers")
                    }
                    else -> {
                        isLoading = false
                    }
                }

            } catch (exception: Exception) {
                isLoading = false
                Log.d("Network", "getGainer${timeFrame}CoinGecko: ${exception.message.toString()}")
            }
        }

    }

    fun loadGainerDexTools(timeFrame: String) {
        isLoading = true
//        if(viewModelScope.isActive)
//            viewModelScope.
        viewModelScope.launch(Dispatchers.Default) {
            try {
                when (val response = cryptoRepository.getGainerDexTools(timeFrame)) {
                    is Resource.Success -> {
                        cryptoList = response.data!!
                        if (cryptoList.isNotEmpty()) isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "getGainer${timeFrame}DexTools: Failed getting Gainers")
                    }
                    else -> {
                        isLoading = false
                    }
                }

            } catch (exception: Exception) {
                isLoading = false
                Log.d("Network", "getGainer${timeFrame}DexTools: ${exception.message.toString()}")
            }
        }
    }

    fun loadGainerCoinMarketCap(timeFrame: String) {
        isLoading = true
        viewModelScope.launch(Dispatchers.Default) {
            try {
                when (val response = cryptoRepository.getGainerCoinMarketCap(timeFrame)) {
                    is Resource.Success -> {
                        cryptoList = response.data!!
                        if (cryptoList.isNotEmpty()) isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "loadGainer${timeFrame}CoinMarketCap: Failed getting Gainers")
                    }
                    else -> {
                        isLoading = false
                    }
                }

            } catch (exception: Exception) {
                isLoading = false
                Log.d("Network", "loadGainer${timeFrame}CoinMarketCap: ${exception.message.toString()}")
            }
        }
    }

    fun load(volumeState: String, remoteApi: String) {
        when (remoteApi) {
            CryptoAnalyzerApis.DexTools.name -> {
                when (volumeState) {
                    "24h" -> loadGainerDexTools("24h")
                    "6h" -> loadGainerDexTools("6h")
                    "1h" -> loadGainerDexTools("1h")
                    "5m" -> loadGainerDexTools("5m")
                }
            }
            CryptoAnalyzerApis.CoinGecko.name -> {
                when (volumeState) {
                    "1y" -> loadGainerCoinGecko("y1")
                    "60d" -> loadGainerCoinGecko("d60")
                    "30d" -> loadGainerCoinGecko("d30")
                    "14d" -> loadGainerCoinGecko("d14")
                    "7d" -> loadGainerCoinGecko("d7")
                    "24h" -> loadGainerCoinGecko("h24")
                    "1h" -> loadGainerCoinGecko("h1")
                }
//                loadNewCoinsCoinGecko()
            }
            CryptoAnalyzerApis.DexScreener.name -> {}
            CryptoAnalyzerApis.CoinMarketCap.name -> {
                when (volumeState) {
                    "30d" -> loadGainerCoinMarketCap("30d")
                    "7d" -> loadGainerCoinMarketCap("7d")
                    "24h" -> loadGainerCoinMarketCap("24h")
                    "1h" -> loadGainerCoinMarketCap("1h")
                }
            }
        }

    }

    fun exportToExcel(onSuccess: () -> Unit = {}, onFail: (String) -> Unit = {}) {
        viewModelScope.launch(Dispatchers.IO) {
            exportToExcelFile(cryptoList, onSuccess = onSuccess, onFail = onFail)
        }

    }


}