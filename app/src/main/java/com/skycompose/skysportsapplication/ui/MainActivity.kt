package com.skycompose.skysportsapplication.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.skycompose.skysportsapplication.ui.components.SkySportsNavGraph
import com.skycompose.skysportsapplication.ui.theme.SkySportsApplicationTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent { SkySportsNewsApp() }
    }
}

@Composable
fun SkySportsNewsApp() {
    SkySportsApplicationTheme {
        ProvideWindowInsets {

            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {
                SkySportsNavGraph(
                    navController = navController
                )
            }
        }
    }
}
