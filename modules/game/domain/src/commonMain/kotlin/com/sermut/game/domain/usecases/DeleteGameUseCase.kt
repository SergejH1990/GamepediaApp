package com.sermut.game.domain.usecases

import com.sermut.game.domain.repository.GameRepository

class GetDeleteGameUseCase(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(id:Int) = gameRepository.Delete(id)
}