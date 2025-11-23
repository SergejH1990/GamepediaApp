package com.sermut.gamepediaapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sermut.game.ui.game.GameScreen

object GameNavigationGraph : BaseNavigationGraph {
    sealed class Destination(val route: String) {
        data object Root : Destination("/game-root")
        data object Game : Destination("/game")
    }
    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(route = Destination.Root.route, startDestination = Destination.Game.route) {
            composable(route = Destination.Game.route){
                GameScreen(modifier = modifier.fillMaxSize(), onFavoriteClick = {

                })
            }
        }
    }
}