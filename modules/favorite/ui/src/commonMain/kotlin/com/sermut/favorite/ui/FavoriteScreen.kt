package com.sermut.favorite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sermut.common.domain.model.Game
import com.sermut.common.ui.listitem.GameItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onDetails: (Int) -> Unit
) {
    val viewModel = koinViewModel<FavoriteViewModel>()
    val games = viewModel.games.collectAsStateWithLifecycle()

    FavoriteScreenContent(
        modifier = modifier.fillMaxSize(),
        games = games.value,
        onBackClick = onBackClick,
        onDetails = onDetails,
        onDelete = {
            viewModel.delete(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    games: List<Game>,
    onBackClick: () -> Unit,
    onDetails: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Favorites")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable { onBackClick() })
                },
                modifier = Modifier.background(color = Color.White)
            )
        }
    ) {
        if (games.isEmpty()) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Nothing found")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = games) { item ->
                    GameItem(
                        isDeleteShown = true,
                        item = item,
                        onClick = onDetails,
                        onDeleteClick = onDelete
                    )
                }
            }
        }
    }
}