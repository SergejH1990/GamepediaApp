package com.sermut.favorite.domain.useCases

import com.sermut.favorite.domain.repository.FavoriteRepository

class DeleteUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id: Int) = favoriteRepository.delete(id = id)
}