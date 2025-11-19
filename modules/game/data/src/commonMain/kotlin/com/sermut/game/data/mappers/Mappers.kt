package com.sermut.game.data.mappers

import com.sermut.corenetwork.model.game.Result
import com.sermut.game.domain.model.Game

fun List<Result>.toDomainListOfGames(): List<Game> = map {
    Game(
        id = it.id,
        name = it.name,
        imageURL = it.background_image,
        genres = it.genres
    )
}