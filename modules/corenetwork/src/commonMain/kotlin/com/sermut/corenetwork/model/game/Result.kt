package com.sermut.corenetwork.model.game

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val background_image: String,
    val genres: List<Genre>,
    val id: Int,
    val name: String,
)