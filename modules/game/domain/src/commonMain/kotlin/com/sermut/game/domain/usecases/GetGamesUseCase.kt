package com.sermut.game.domain.usecases

import com.sermut.game.domain.model.Game
import com.sermut.game.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGamesUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke() = flow<Result<List<Game>>> {
        emit(gameRepository.GetGames())
    }.catch { error->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.IO)
}