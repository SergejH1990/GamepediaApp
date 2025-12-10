package com.sermut.game.ui.gamedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameDetailsScreen(
    modifiler : Modifier = Modifier,
    id: String
){
    val viewModel = koinViewModel<GameDetailsViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id){
        viewModel.GetGameDetails(id.toInt())
    }

    GameDetailsScreenContent(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState.value
    )
}

@Composable
fun GameDetailsScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameDetailsScreen.UIState
){
    if (uiState.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }

    if (uiState.error.isNotBlank()){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(uiState.error)
        }
    }

    uiState.data?.let { data ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp)
            ){
                item {
                    AsyncImage(
                        model = data.backgroundImage,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        text = data.name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                item {
                    Text(
                        text = data.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                                text = "Platforms",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                                    .padding(top = 16.dp)
                            )

                        LazyRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(items = data.platforms){
                                Card(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(12.dp)
                                        .background(color = Color.White),
                                    shape = RoundedCornerShape(12.dp),
                                    elevation = CardDefaults.cardElevation(6.dp)
                                ){
                                    Column(
                                        modifier = Modifier
                                            .width(150.dp)
                                    ) {
                                        AsyncImage(
                                            model = it.image,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .background(
                                                    color = Color.Transparent,
                                                    shape = CircleShape
                                                ).clip(CircleShape)
                                        )

                                        Text(
                                            text = it.name,
                                            style = MaterialTheme.typography.titleSmall,
                                            modifier = Modifier
                                                .padding(vertical = 12.dp)
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