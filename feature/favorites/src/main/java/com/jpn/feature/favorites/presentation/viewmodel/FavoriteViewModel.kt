package com.jpn.feature.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.feature.favorites.domain.model.Favorite
import com.jpn.feature.favorites.domain.usecase.AddFavoriteUseCase
import com.jpn.feature.favorites.domain.usecase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase
) : ViewModel() {

    val favorites: StateFlow<List<Favorite>> = getFavoritesUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addFavorite(favorite: Favorite) {
        viewModelScope.launch {
            addFavoriteUseCase(favorite)
        }
    }
}