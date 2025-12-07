package com.sermut.gamepediaapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sermut.gamepediaapp.navigation.GameNavigationGraph
import com.sermut.gamepediaapp.navigation.SearchNavigationGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()
        NavHost(navHostController, startDestination = GameNavigationGraph.Destination.Game){
            listOf(
                GameNavigationGraph,
                SearchNavigationGraph
            ).forEach { graphBuilder ->
                graphBuilder.build(
                    modifier = Modifier.fillMaxSize(),
                    navHostController = navHostController,
                    navGraphBuilder = this
                )
            }
        }
    }
}