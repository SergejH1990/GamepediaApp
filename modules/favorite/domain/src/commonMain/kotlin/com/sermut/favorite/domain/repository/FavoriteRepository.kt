package com.sermut.favorite.domain.repository

import com.sermut.common.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun GetAllGames() : Flow<List<Game>>

    suspend fun upsert(id: Int, name: String, image: String)

    suspend fun delete(id: Int)
}