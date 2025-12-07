package com.sermut.game.ui.gamedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sermut.game.domain.model.GameDetails
import com.sermut.game.domain.usecases.GetGameDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class GameDetailsViewModel(
    private val gameDetailsUseCase: GetGameDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameDetailsScreen.UIState())
    val uiState = _uiState.asStateFlow()

    fun GetGameDetails(id: Int){
        gameDetailsUseCase.invoke(id)
            .onStart {
                _uiState.update {
                    GameDetailsScreen.UIState(isLoading = true)
                }
            }.onEach { result ->
                result.onSuccess { data ->
                    _uiState.update { GameDetailsScreen.UIState(data = data) }
                }.onFailure { error ->
                    _uiState.update { GameDetailsScreen.UIState(error = error.message.toString()) }
                }
            }.launchIn(viewModelScope)
    }
}

data object GameDetailsScreen{
    data class UIState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: GameDetails? = null
    )
}