package com.sermut.game.data.repository

import com.sermut.common.data.mappers.toDomainListOfGames
import com.sermut.common.domain.model.Game
import com.sermut.coredatabase.GameDB
import com.sermut.corenetwork.apiservice.ApiService
import com.sermut.game.data.mappers.toDomainGameDetais
import com.sermut.game.domain.model.GameDetails
import com.sermut.game.domain.repository.GameRepository

class GameRepositoryImplementation(
    private val apiService: ApiService,
    private val gameDB: GameDB
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

    override suspend fun GetDetails(id: Int): Result<GameDetails> {
        val result = apiService.GetDetails(id)
        return if (result.isSuccess) {
            Result.success(result.getOrThrow().toDomainGameDetais())
        }
        else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }

    override suspend fun Save(id: Int, image: String, name: String) {
        gameDB.appDatabaseQueries.upsert(id = id.toLong(), image = image, name = name)
    }

    override suspend fun Delete(id: Int) {
        gameDB.appDatabaseQueries.delete(id = id.toLong())
    }
}