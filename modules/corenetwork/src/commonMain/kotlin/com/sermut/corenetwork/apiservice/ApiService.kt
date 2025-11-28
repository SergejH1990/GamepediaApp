package com.sermut.corenetwork.apiservice

import com.sermut.corenetwork.model.game.GameResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService (
    val httpClient : HttpClient
) {
    suspend fun GetGames() : Result<GameResponse> {
        return try {
            val response = httpClient.get("api/games") {
                url{
                    parameter("key", "0c56c669b4194526ba356a980fa914b4")
                }
            }.body<GameResponse>()
            Result.success(response)
        }
        catch (e : Exception){
            Result.failure(e)
        }
    }

    suspend fun Search(searchQuery : String) : Result<GameResponse> {
        return try {
            val response = httpClient.get("api/games") {
                url{
                    parameter("key", "0c56c669b4194526ba356a980fa914b4")
                    parameter("search", searchQuery)
                }
            }.body<GameResponse>()
            Result.success(response)
        }
        catch (e : Exception){
            Result.failure(e)
        }
    }
}