package com.sermut.favorite.domain.useCases

import com.sermut.favorite.domain.repository.FavoriteRepository

class UpsertUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id: Int, image: String, name: String) = favoriteRepository.upsert(id = id, image = image, name = name)
}