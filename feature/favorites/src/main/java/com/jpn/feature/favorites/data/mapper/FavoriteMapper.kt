package com.jpn.feature.favorites.data.mapper

import com.jpn.core.local.entity.FavoriteItemEntity
import com.jpn.feature.favorites.domain.model.Favorite

fun FavoriteItemEntity.toDomain() = Favorite(id, name, imageUrl, description)

fun Favorite.toEntity() =
    FavoriteItemEntity(id, name, imageUrl, description)
