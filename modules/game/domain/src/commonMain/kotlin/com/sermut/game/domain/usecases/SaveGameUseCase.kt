package com.sermut.game.domain.usecases

import com.sermut.game.domain.repository.GameRepository

class GetSaveGameUseCase(
    private val gameRepository: GameRepository
) {
    suspend  operator fun invoke(id: Int, image: String, name: String) = gameRepository.Save(id, image, name)
}