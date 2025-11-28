package com.sermut.game.data.repository

import com.sermut.common.data.mappers.toDomainListOfGames
import com.sermut.common.domain.model.Game
import com.sermut.corenetwork.apiservice.ApiService
import com.sermut.game.domain.repository.GameRepository

class GameRepositoryImplementation(
    private val apiService: ApiService
) : GameRepository {
    override suspend fun GetGames(): Result<List<Game>> {
        val result = apiService.GetGames()
        return if (result.isSuccess) {
            Result.success(result.getOrThrow().results.toDomainListOfGames())
        }
        else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }
}