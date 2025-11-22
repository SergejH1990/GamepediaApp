package com.sermut.game.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit
){
    val viewModel = koinViewModel<GameViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    GameScreenContent(modifier = modifier.fillMaxSize(), uiState = uiState.value, onFavoriteClick)
}

@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScreen.UIState,
    onFavoriteClick: () -> Unit
){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("GamePedia") },
                actions = {
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null
                        )
                    }
                }
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
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(data) {
                    Card(modifier = Modifier.padding(8.dp),
                        shape = RoundedCornerShape(12.dp)
                    ){
                        Box(modifier.fillMaxSize()){
                            AsyncImage(
                                model = it.imageURL,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth().height(350.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                            ){
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}