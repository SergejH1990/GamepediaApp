package com.sermut.favorite.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.sermut.common.domain.model.Game
import com.sermut.common.domain.model.Genre
import com.sermut.coredatabase.GameDB
import com.sermut.favorite.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImplementation(
    private val gameDB: GameDB
) : FavoriteRepository {
    override fun GetAllGames(): Flow<List<Game>> {
        return gameDB.appDatabaseQueries
            .getAllGames()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map {
                it.map { args ->
                    Game(
                        id = args.id.toInt(),
                        name = args.name,
                        imageURL = args.image,
                        genreList = emptyList()
                    )
                }
            }
    }

    override suspend fun upsert(id: Int, name: String, image: String) {
        gameDB.appDatabaseQueries.upsert(id.toLong(), name, image)
    }

    override suspend fun delete(id: Int) {
        gameDB.appDatabaseQueries.delete(id.toLong())
    }
}