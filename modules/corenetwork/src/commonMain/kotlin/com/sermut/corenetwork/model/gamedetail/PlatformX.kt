package com.sermut.corenetwork.model.gamedetail

import kotlinx.serialization.Serializable

@Serializable
data class PlatformX(
    val platform: PlatformXX,
    val released_at: String,
)