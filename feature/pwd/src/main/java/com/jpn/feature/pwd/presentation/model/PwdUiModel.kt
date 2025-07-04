package com.jpn.feature.notes.presentation.model

data class PwdUiModel(
    val id: Int,
    val key: String,
    val value: String,
    val others:List<String>,
    val createdAt: String,
    val updatedAt: String
)
