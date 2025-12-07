package com.sermut.gamepediaapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sermut.search.ui.SearchScreen
import kotlinx.serialization.Serializable

object SearchNavigationGraph : BaseNavigationGraph {

    sealed interface Destination{
       @Serializable data object Search : Destination
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.composable<Destination.Search> {
            SearchScreen(
                modifier = Modifier.fillMaxSize(),
                onClick = {

                }
            )
        }
    }
}