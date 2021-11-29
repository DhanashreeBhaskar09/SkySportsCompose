package com.skycompose.skysportsapplication.ui

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import  com.skycompose.skysportsapplication.ui.news.HomeScreenViewModel
import  com.skycompose.skysportsapplication.ui.news.HomeScreen

object MainDestinations {
  const val HOME_ROUTE = "home"
}

@Composable
fun SkySportsNavGraph(
    applicationContext: Context,
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    startDestination: String = MainDestinations.HOME_ROUTE
) {

  val actions = remember(navController) { MainActions(navController) }
  val coroutineScope = rememberCoroutineScope()
  val openDrawer: () -> Unit = { coroutineScope.launch { scaffoldState.drawerState.open() } }

  NavHost(navController = navController, startDestination = startDestination) {
    composable(MainDestinations.HOME_ROUTE) {
      val homeViewModel: HomeScreenViewModel =
          viewModel(factory = HomeScreenViewModel.provideFactory(applicationContext))
      HomeScreen(homeViewModel = homeViewModel)
    }
  }
}

class MainActions(navController: NavHostController) {
  val upPress: () -> Unit = { navController.navigateUp() }
}
