package com.appsepehr.cryptoanalyzer.navigation

enum class CryptoAnalyzerScreens {
    SplashScreen,
    HomeScreen,
    WatchListScreen;

    companion object {
        fun fromRoute(route: String?): CryptoAnalyzerScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                HomeScreen.name -> HomeScreen
                WatchListScreen.name -> WatchListScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route ${route} is not recognized.")
            }


    }
}