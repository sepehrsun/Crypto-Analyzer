package com.appsepehr.cryptoanalyzer.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appsepehr.cryptoanalyzer.components.ApiButton
import com.appsepehr.cryptoanalyzer.components.AppTopBar
import com.appsepehr.cryptoanalyzer.components.CryptoTable
import com.appsepehr.cryptoanalyzer.components.VolumeButton
import com.appsepehr.cryptoanalyzer.data.model.Crypto
import com.appsepehr.cryptoanalyzer.data.remote.CryptoAnalyzerApis
import com.appsepehr.cryptoanalyzer.utils.showToast
import kotlinx.coroutines.*

@Composable
fun HomeScreen(
    navController: NavController, viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(modifier = Modifier.padding(4.dp),
        topBar = {
            AppTopBar(title = "Crypto Analyzer", navController = navController)
        }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HomeContent(navController = navController, viewModel = viewModel)

        }
    }
}

@Composable
fun HomeContent(navController: NavController, viewModel: HomeViewModel) {
    val gainerMutableStateList = remember { mutableStateOf(listOf<Crypto>()) }
    gainerMutableStateList.value = viewModel.cryptoList

    val volumeState = remember { mutableStateOf("24h") }
    val apiState = remember { mutableStateOf(CryptoAnalyzerApis.DexTools.name) }
    val timeFrameStateList = remember { mutableStateOf(listOf("24h","6h","1h","5m")) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ApiRow(viewModel, volumeState, apiState,timeFrameStateList)
//            VolumeRow(viewModel, volumeState, apiState)
            VolumeRowList(viewModel,volumeState, apiState ,timeFrameStateList)

            if (viewModel.isLoading)
                CircularProgressIndicator()
            else if (!viewModel.isLoading && gainerMutableStateList.value.isEmpty())
                ReloadSection() {
                    viewModel.load(volumeState.value, apiState.value)
                }
            else {
                Column {
                    ExportRow(viewModel)
                    CryptoTable(gainerMutableStateList)
                }

            }

        }


    }
}

@Composable
fun ApiRow(
    viewModel: HomeViewModel,
    volumeState: MutableState<String>,
    apiState: MutableState<String>,
    timeFrameStateList: MutableState<List<String>>,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ApiButton(
            title = "DexTools",
            isSelected = apiState.value == CryptoAnalyzerApis.DexTools.name
        ) {
            apiState.value = CryptoAnalyzerApis.DexTools.name
            viewModel.load(volumeState.value, apiState.value)
            timeFrameStateList.value = listOf("24h","6h","1h","5m")
        }
        Spacer(modifier = Modifier.width(10.dp))

        ApiButton(
            title = "CoinGecko",
            isSelected = apiState.value == CryptoAnalyzerApis.CoinGecko.name
        ) {
            apiState.value = CryptoAnalyzerApis.CoinGecko.name
            viewModel.load(volumeState.value, apiState.value)
            timeFrameStateList.value = listOf("1y","30d","7d","24h","1h")
        }

        Spacer(modifier = Modifier.width(10.dp))

        ApiButton(
            title = "CoinMarketCap",
            isSelected = apiState.value == CryptoAnalyzerApis.CoinMarketCap.name
        ) {
            apiState.value = CryptoAnalyzerApis.CoinMarketCap.name
            viewModel.load(volumeState.value, apiState.value)
            timeFrameStateList.value = listOf("30d","7d","24h","1h")
        }

    }
}


@Composable
private fun VolumeRow(
    viewModel: HomeViewModel,
    volumeState: MutableState<String>,
    apiState: MutableState<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        VolumeButton(title = "24H", isSelected = volumeState.value == "24H") {
            volumeState.value = "24H"
            viewModel.load(volumeState.value, apiState.value)
        }
        Spacer(modifier = Modifier.width(20.dp))

        VolumeButton(title = "6H", isSelected = volumeState.value == "6H") {
            volumeState.value = "6H"
            viewModel.load(volumeState.value, apiState.value)
        }

        Spacer(modifier = Modifier.width(20.dp))

        VolumeButton(title = "1H", isSelected = volumeState.value == "1H") {
            volumeState.value = "1H"
            viewModel.load(volumeState.value, apiState.value)
        }

    }
}

@Composable
private fun VolumeRowList(
    viewModel: HomeViewModel,
    volumeState: MutableState<String>,
    apiState: MutableState<String>,
    timeFrameStateList: MutableState<List<String>>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        timeFrameStateList.value.forEach {timeFrame->
            VolumeButton(title = timeFrame, isSelected = volumeState.value == timeFrame) {
                volumeState.value = timeFrame
                viewModel.load(volumeState.value, apiState.value)
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

    }
}

@Composable
fun ReloadSection(onClickReload: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
                    .clickable { onClickReload.invoke() },
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh iocn")
                Text(text = "Reload")
            }
        }

    }
}

@Composable
private fun ExportRow(viewModel: HomeViewModel) {
    val context = LocalContext.current
    val loading = remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

        if (!loading.value) {
            Button(onClick = {
                loading.value = true
                viewModel.exportToExcel(onSuccess = {
                    loading.value = false
                    MainScope().launch {
                        showToast(context, "Export Successful to Downloads Folder")
                    }
                }, onFail = { error ->
                    loading.value = false
                    MainScope().launch { showToast(context, "Got an Error: ${error}") }
                })

            }) {
                Text(text = "Export to excel")
            }
        } else
            CircularProgressIndicator()
    }
}
