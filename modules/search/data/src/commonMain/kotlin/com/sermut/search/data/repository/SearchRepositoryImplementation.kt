package com.sermut.search.data.repository

import com.sermut.common.data.mappers.toDomainListOfGames
import com.sermut.common.domain.model.Game
import com.sermut.corenetwork.apiservice.ApiService
import com.sermut.search.domain.repository.SearchRepository

class SearchRepositoryImplementation(
    private val apiService: ApiService
) : SearchRepository {
    override suspend fun Search(searchQuery: String): Result<List<Game>> {
        return try {
            val response = apiService.Search(searchQuery)
            Result.success(response.getOrThrow().results.toDomainListOfGames())
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}