package com.sermut.corenetwork.model.game

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)