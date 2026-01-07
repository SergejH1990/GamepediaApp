package com.sermut.favorite.domain.useCases

import com.sermut.favorite.domain.repository.FavoriteRepository

class GetAllLocalGamesUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke() = favoriteRepository.GetAllGames()
}