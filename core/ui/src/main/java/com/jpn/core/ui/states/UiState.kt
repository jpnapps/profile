package com.jpn.core.ui.states

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<UiState<T>> = flow {
    emit(UiState.Loading)
    try {
        collect { emit(UiState.Success(it)) }
    } catch (e: Exception) {
        emit(UiState.Error(e.message ?: "Unknown Error"))
    }
}