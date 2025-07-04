package com.jpn.feature.notes.presentation.add

data class AddNoteFormState(
    val title: String = "",
    val content: String = "",
    val imageLink: String = "",
    val refLinks: List<String> = emptyList(),
    val titleError: String? = null,
    val contentError: String? = null,
    val linkInput: String = "",
    val linkError: String? = null
)
