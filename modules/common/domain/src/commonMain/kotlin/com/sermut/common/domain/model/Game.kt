package com.sermut.common.domain.model

data class Game(
    val id: Int,
    val name: String,
    val imageURL: String,
    val genreList: List<Genre>,
)
