package com.jpn.feature.favorites.domain.usecase

import com.jpn.feature.favorites.domain.repository.FavoriteRepository

class GetFavoritesUseCase(private val repository: FavoriteRepository) {
    operator fun invoke() = repository.getFavorites()
}
