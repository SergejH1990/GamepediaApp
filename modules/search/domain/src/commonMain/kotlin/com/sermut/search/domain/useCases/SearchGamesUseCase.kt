package com.sermut.search.domain.useCases

import com.sermut.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchGamesUseCase(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(searchQuery: String) = flow {
        emit(searchRepository.Search(searchQuery))
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.IO)
}