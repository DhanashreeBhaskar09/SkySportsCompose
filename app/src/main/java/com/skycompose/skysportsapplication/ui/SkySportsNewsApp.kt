package com.skycompose.skysportsapplication.ui

import android.content.Context
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skycompose.skysportsapplication.ui.theme.SkySportsApplicationTheme

@Composable
fun SkySportsNewsApp(context: Context) {
  SkySportsApplicationTheme {
    ProvideWindowInsets {
      val systemUiController = rememberSystemUiController()
      SideEffect { systemUiController.setSystemBarsColor(Color.Transparent) }

      val navController = rememberNavController()
      val scaffoldState = rememberScaffoldState()

      Scaffold(scaffoldState = scaffoldState) {
        SkySportsNavGraph(
            applicationContext = context,
            navController = navController,
            scaffoldState = scaffoldState)
      }
    }
  }
}
