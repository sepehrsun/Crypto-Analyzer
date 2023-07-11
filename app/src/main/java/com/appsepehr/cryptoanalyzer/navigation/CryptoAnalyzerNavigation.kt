package com.appsepehr.cryptoanalyzer.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appsepehr.cryptoanalyzer.screens.home.HomeScreen
import com.appsepehr.cryptoanalyzer.screens.home.HomeViewModel
import com.appsepehr.cryptoanalyzer.screens.splash.SplashScreen

@Composable
fun CryptoAnalyzerNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CryptoAnalyzerScreens.SplashScreen.name
    ) {
        composable(route = CryptoAnalyzerScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(route = CryptoAnalyzerScreens.HomeScreen.name) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController = navController, viewModel = viewModel)
        }


    }
}
