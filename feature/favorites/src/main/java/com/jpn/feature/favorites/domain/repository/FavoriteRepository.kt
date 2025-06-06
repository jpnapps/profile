package com.jpn.feature.favorites.domain.repository

import com.jpn.feature.favorites.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorites(): Flow<List<Favorite>>
    suspend fun addFavorite(favorite: Favorite)
}