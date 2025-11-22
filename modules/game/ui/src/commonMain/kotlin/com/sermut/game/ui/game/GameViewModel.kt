package com.sermut.game.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sermut.game.domain.model.Game
import com.sermut.game.domain.usecases.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class GameViewModel(
    private val gameUseCase: GetGamesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameScreen.UIState())
    val uiState = _uiState.asStateFlow()

    init {
        GetGames()
    }

    fun GetGames() = gameUseCase.invoke()
        .onStart {
            _uiState.update { GameScreen.UIState(isLoading = true) }
        }.onEach {result ->
            result.onSuccess { data ->
                _uiState.update { GameScreen.UIState(data = data) }
            }.onFailure { error ->
                _uiState.update { GameScreen.UIState(error = error.message.toString()) }
            }
        }.launchIn(viewModelScope)
}

object GameScreen {
    data class UIState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<Game>? = null
    )
}