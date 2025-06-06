package com.jpn.feature.favorites.domain.usecase

import com.jpn.feature.favorites.domain.model.Favorite
import com.jpn.feature.favorites.domain.repository.FavoriteRepository

class AddFavoriteUseCase(private val repository: FavoriteRepository) {
    suspend operator fun invoke(favorite: Favorite) = repository.addFavorite(favorite)
}