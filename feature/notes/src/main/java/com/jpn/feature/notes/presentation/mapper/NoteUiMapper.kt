package com.jpn.feature.favorites.data.mapper

import com.jpn.core.utils.extensions.toFormattedDate
import com.jpn.domain.model.Note
import com.jpn.feature.notes.presentation.model.NoteUiModel

fun Note.toUIModel(): NoteUiModel =
    NoteUiModel(id, title, content, imageLink, refLinks, createdAt.toFormattedDate())
