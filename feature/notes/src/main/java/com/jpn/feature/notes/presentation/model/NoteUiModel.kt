package com.jpn.feature.notes.presentation.model

data class NoteUiModel(
    val id: Int,
    val title: String,
    val content: String,
    val imageLink:String?,
    val refLinks:List<String>,
    val createdAt: String
)
