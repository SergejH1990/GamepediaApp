package com.sermut.search.domain.repository

import com.sermut.common.domain.model.Game

interface SearchRepository {
    suspend fun Search(searchQuery: String) : Result<List<Game>>
}