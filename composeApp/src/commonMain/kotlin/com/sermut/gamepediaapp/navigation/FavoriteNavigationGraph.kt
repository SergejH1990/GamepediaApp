package com.sermut.gamepediaapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sermut.favorite.ui.FavoriteScreen
import kotlinx.serialization.Serializable

object FavoriteNavigationGraph : BaseNavigationGraph {

    sealed interface Destination{
        @Serializable data object Favorite : Destination
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.composable<Destination.Favorite> {
            FavoriteScreen(
                modifier = Modifier.fillMaxSize(),
                onBackClick = {
                    navHostController.popBackStack()
                },
                onDetails = { id ->
                    navHostController.navigate(GameNavigationGraph.Destination.Details(id = id.toString()))
                }
            )
        }
    }
}