package com.jpn.feature.favorites.domain.model

data class Favorite(
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val description: String
)