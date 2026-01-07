package com.sermut.game.domain.usecases

import com.sermut.game.domain.model.GameDetails
import com.sermut.game.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGameDetailsUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(id: Int) = flow<Result<GameDetails>> {
        emit(gameRepository.GetDetails(id))
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.IO)
}