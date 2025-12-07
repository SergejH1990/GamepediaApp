package com.sermut.corenetwork.model.gamedetail

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val language: String,
    val name: String,
    val slug: String
)