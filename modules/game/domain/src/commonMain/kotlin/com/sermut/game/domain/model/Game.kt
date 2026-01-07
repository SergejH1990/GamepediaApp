package com.sermut.game.domain.model

import com.sermut.corenetwork.model.game.Genre

data class Game(
    val id: Int,
    val name: String,
    val imageURL: String,
    val genres: List<Genre>,
)
