package com.sermut.corenetwork.model.gamedetail

import kotlinx.serialization.Serializable

@Serializable
data class PlatformXX(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String,
)