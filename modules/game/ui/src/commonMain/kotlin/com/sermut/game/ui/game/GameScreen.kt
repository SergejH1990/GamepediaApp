package com.sermut.game.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.sermut.common.domain.model.Genre
import com.sermut.common.ui.listitem.GameItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClick: (Int) -> Unit
){
    val viewModel = koinViewModel<GameViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    GameScreenContent(
        modifier = modifier.fillMaxSize(),
        uiState = uiState.value,
        onFavoriteClick = onFavoriteClick,
        onSearchClick = onSearchClick,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScreen.UIState,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClick: (Int) -> Unit
){
    Scaffold(
        modifier = modifier.fillMaxSize().background(Color.White),
        topBar = {
            TopAppBar(
                modifier = Modifier.background(color = Color.White),
                title = { Text("GamePedia") },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) {
        if (uiState.isLoading){
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }

        if (uiState.error.isNotBlank()){
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(uiState.error)
            }
        }

        uiState.data?.let { data ->
            val kTopAppBarPadding = 100.dp
            val kBottomBarPadding = 50.dp
            LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = kTopAppBarPadding, bottom = kBottomBarPadding)
            ){
                items(data) { item ->
                    GameItem(
                        isDeleteShown = false,
                        item = item,
                        onClick = onClick,
                        onDeleteClick = {}
                    )
                }
            }
        }
    }
}