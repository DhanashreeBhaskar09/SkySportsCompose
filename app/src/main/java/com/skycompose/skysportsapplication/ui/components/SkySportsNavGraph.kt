package com.skycompose.skysportsapplication.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skycompose.skysportsapplication.ui.news.HomeScreen
import com.skycompose.skysportsapplication.ui.news.HomeScreenViewModel

object MainDestinations {
    const val HOME_ROUTE = "home"
}

@Composable
fun SkySportsNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = startDestination) {
        composable(MainDestinations.HOME_ROUTE) {
            val homeViewModel: HomeScreenViewModel =
                viewModel(factory = HomeScreenViewModel.provideFactory(context))
            HomeScreen(homeViewModel = homeViewModel)
        }
    }
}