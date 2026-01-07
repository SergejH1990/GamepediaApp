package com.sermut.corenetwork.model.gamedetail

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val id: Int,
    val store: StoreX,
    val url: String
)