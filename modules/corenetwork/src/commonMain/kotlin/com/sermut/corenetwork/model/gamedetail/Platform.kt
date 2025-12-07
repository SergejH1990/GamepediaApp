package com.sermut.corenetwork.model.gamedetail

import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    val id: Int,
    val name: String,
    val slug: String
)