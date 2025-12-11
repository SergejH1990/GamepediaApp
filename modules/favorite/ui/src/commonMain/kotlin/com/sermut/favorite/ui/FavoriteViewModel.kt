package com.sermut.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sermut.favorite.domain.useCases.DeleteUseCase
import com.sermut.favorite.domain.useCases.GetAllLocalGamesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val deleteUseCase: DeleteUseCase,
    private val getAllLocalGamesUseCase: GetAllLocalGamesUseCase
) : ViewModel() {

    val games = getAllLocalGamesUseCase
        .invoke()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun delete(id: Int) = viewModelScope.launch {
        deleteUseCase.invoke(id)
    }
}