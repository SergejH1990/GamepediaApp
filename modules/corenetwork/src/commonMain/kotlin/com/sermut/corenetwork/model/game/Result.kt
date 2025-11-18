package com.sermut.corenetwork.model.game

data class Result(
    val background_image: String,
    val genres: List<Genre>,
    val id: Int,
    val name: String,
    val rating: Double,
    val rating_top: Int,
    val ratings: List<Rating>,
)