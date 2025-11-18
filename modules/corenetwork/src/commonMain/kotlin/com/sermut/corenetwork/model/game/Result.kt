package com.sermut.corenetwork.model.game

data class Result(
    val background_image: String,
    val genres: List<Genre>,
    val id: Int,
    val name: String,
)