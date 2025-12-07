package com.sermut.gamepediaapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sermut.game.ui.game.GameScreen
import com.sermut.game.ui.gamedetails.GameDetailsScreen
import kotlinx.serialization.Serializable

object GameNavigationGraph : BaseNavigationGraph {
    sealed interface Destination {
        @Serializable data object Game : Destination
        @Serializable data class Details(val id: String): Destination
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.composable<Destination.Game>{
            GameScreen(
                modifier = modifier.fillMaxSize(),
                onFavoriteClick = {

                },
                onSearchClick =  {
                    println("Attempting to navigate to Search")
                    navHostController.navigate(SearchNavigationGraph.Destination.Search)
                },
                onClick = { id ->
                    println("Attempting to navigate to Details with ID: $id")
                    navHostController.navigate(Destination.Details(id = id.toString()))
                }
            )
        }

        navGraphBuilder.composable<Destination.Details> { backStackEntry ->
            val id = backStackEntry.toRoute<Destination.Details>()
            GameDetailsScreen(
                modifiler = Modifier.fillMaxSize(),
                id = id.id
            )
        }
    }
}