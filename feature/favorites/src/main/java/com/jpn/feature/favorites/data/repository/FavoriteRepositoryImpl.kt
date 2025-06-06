package com.jpn.feature.favorites.data.repository

import com.jpn.feature.favorites.data.local.dao.FavoriteDao
import com.jpn.feature.favorites.data.mapper.toDomain
import com.jpn.feature.favorites.data.mapper.toEntity
import com.jpn.feature.favorites.domain.model.Favorite
import com.jpn.feature.favorites.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoriteRepository {
    override fun getFavorites(): Flow<List<Favorite>> =
        dao.getAllFavorites().map { list -> list.map { it.toDomain() } }

    override suspend fun addFavorite(favorite: Favorite) {
        dao.insertFavorite(favorite.toEntity())
    }
}