package com.sermut.game.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
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
            val kTopAppBarPadding = 100.dp
            val kBottomBarPadding = 50.dp
            LazyColumn(modifier = Modifier.fillMaxSize().padding(top = kTopAppBarPadding, bottom = kBottomBarPadding)){
                items(data) {
                    val kCardPadding = 8.dp
                    val kCardCornerRadius = 12.dp
                    val kTextHorizontalPadding = 8.dp
                    val kTextVerticalPadding = 4.dp
                    Card(modifier = Modifier.padding(kCardPadding),
                        shape = RoundedCornerShape(kCardCornerRadius)
                    ){
                        val kCardContentPadding = 20.dp
                        Box(modifier.fillMaxSize().padding(all = kCardContentPadding)){
                            val kImageHeight = 350.dp
                            AsyncImage(
                                model = it.imageURL,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth().height(kImageHeight)
                            )

                            Box(
                                modifier = Modifier.background(Color.White, shape = RoundedCornerShape(kCardCornerRadius))
                            ){
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(horizontal = kTextHorizontalPadding, vertical = kTextVerticalPadding),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Row(modifier = Modifier.padding(start = kCardContentPadding)){
                            val kGenreBoxPadding = 4.dp
                            Box(
                                modifier = Modifier
                                    .padding(vertical = kGenreBoxPadding, )
                            ) {
                                Text(
                                    text = "Genre:",
                                    modifier = Modifier.padding(
                                        horizontal = kTextHorizontalPadding,
                                        vertical = kTextVerticalPadding
                                    )
                                )
                            }
                            LazyRow(modifier = Modifier.fillMaxSize()){
                                items(items = it.genreList){
                                    Box(
                                        modifier = Modifier
                                            .padding(kGenreBoxPadding)
                                            .background(Color.White, shape = RoundedCornerShape(kCardCornerRadius))
                                    ) {
                                        Text(
                                            text = it.name,
                                            modifier = Modifier.padding(
                                                horizontal = kTextHorizontalPadding,
                                                vertical = kTextVerticalPadding
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}