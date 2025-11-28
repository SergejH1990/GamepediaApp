package com.sermut.game.domain.repository

import com.sermut.common.domain.model.Game

interface GameRepository {
    suspend fun GetGames(): Result<List<Game>>
}