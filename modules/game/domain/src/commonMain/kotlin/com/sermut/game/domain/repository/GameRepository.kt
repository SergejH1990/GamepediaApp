package com.sermut.game.domain.repository

import com.sermut.common.domain.model.Game
import com.sermut.game.domain.model.GameDetails

interface GameRepository {
    suspend fun GetGames(): Result<List<Game>>

    suspend fun GetDetails(id: Int): Result<GameDetails>
}