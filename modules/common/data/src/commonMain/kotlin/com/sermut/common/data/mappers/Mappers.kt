package com.sermut.common.data.mappers

import com.sermut.common.domain.model.Game
import com.sermut.corenetwork.model.game.Result

fun List<Result>.toDomainListOfGames(): List<Game> = map {
    Game(
        id = it.id,
        name = it.name,
        imageURL = it.background_image,
        genreList = it.genres
    )
}