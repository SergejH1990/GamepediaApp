package com.sermut.gamepediaapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sermut.search.ui.SearchScreen

object SearchNavigationGraph : BaseNavigationGraph {

    sealed class Destination(val route : String){
        data object Root : Destination("/search-root")
        data object Search : Destination("/search")
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            route = Destination.Root.route,
            startDestination = Destination.Search.route
        ) {
            composable(route = Destination.Search.route) {
                SearchScreen(
                    modifier = Modifier.fillMaxSize(),
                    onClick = {

                    }
                )
            }
        }
    }
}