package com.sermut.game.ui.game

import androidx.compose.foundation.background
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
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit
){
    val viewModel = koinViewModel<GameViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    GameScreenContent(modifier = modifier.fillMaxSize(), uiState = uiState.value, onFavoriteClick, onSearchClick)
}

@Composable
fun GameScreenGenreRow(
    modifier: Modifier = Modifier,
    genreList: List<Genre>
){
    val kTextHorizontalPadding = 8.dp
    val kTextVerticalPadding = 4.dp
    val kCardContentPadding = 20.dp
    val kCardCornerRadius = 12.dp
    val genreRowBackgroundColor = Color(Color.Cyan.red, Color.Cyan.green, Color.Cyan.blue, alpha = 0.75f, colorSpace = Color.Cyan.colorSpace)
    Box(modifier = Modifier.background(genreRowBackgroundColor, shape = RoundedCornerShape(kCardCornerRadius)).fillMaxWidth())
    {
        Row(modifier = Modifier.padding(start = kCardContentPadding).align(Alignment.Center)) {
            val kGenreBoxPadding = 4.dp
            Text(
                text = "Genre:",
                modifier = Modifier.padding(
                    horizontal = kTextHorizontalPadding,
                    vertical = kTextVerticalPadding + kGenreBoxPadding
                )
            )
            LazyRow() {
                items(items = genreList) {
                    Box(
                        modifier = Modifier
                            .padding(kGenreBoxPadding)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScreen.UIState,
    onFavoriteClick: () -> Unit,
    onSearchClick: () -> Unit
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
                items(data) {
                    val kCardPadding = 12.dp
                    val kCardCornerRadius = 12.dp
                    val kTextHorizontalPadding = 8.dp
                    val kTextVerticalPadding = 15.dp
                    val kCardHeight = 400.dp
                    val kImageHeight = 350.dp
                    Card(
                        modifier = Modifier
                            .padding(kCardPadding)
                            .fillMaxWidth()
                            .height(kCardHeight),
                        shape = RoundedCornerShape(kCardCornerRadius),
                    ){
                        Box(modifier
                            .fillMaxSize()
                            .padding(kCardPadding)
                            .background(Color.White, shape = RoundedCornerShape(kCardCornerRadius))
                        ){
                            AsyncImage(
                                model = it.imageURL,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(kImageHeight)
                            )

                            val kNameGenreHorizontalPadding = 4.dp
                            Box(modifier = Modifier.align(Alignment.BottomCenter).padding(horizontal = kNameGenreHorizontalPadding)){
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    val kTextShadowOffset =  Offset(5f,  -5f)
                                    val kTextBlurRadius = 5f
                                    Text(
                                        text = it.name,
                                        fontSize = 20.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        style = TextStyle(shadow = Shadow(color = Color.Cyan, offset = kTextShadowOffset, blurRadius = kTextBlurRadius)),
                                        modifier = Modifier
                                            .padding(
                                                horizontal = kTextHorizontalPadding,
                                                vertical = kTextVerticalPadding
                                            )
                                            .align(Alignment.CenterHorizontally),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    GameScreenGenreRow(genreList = it.genreList, modifier = Modifier.align(Alignment.CenterHorizontally))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}