package com.sermut.corenetwork.model.gamedetail

import kotlinx.serialization.Serializable

@Serializable
data class EsrbRating(
    val id: Int,
    val name: String,
    val slug: String
)