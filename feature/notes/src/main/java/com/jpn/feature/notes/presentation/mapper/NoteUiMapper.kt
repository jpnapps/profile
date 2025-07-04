package com.jpn.feature.favorites.data.mapper

import com.jpn.core.utils.extensions.toFormattedDate
import com.jpn.domain.profile.model.Note
import com.jpn.feature.notes.presentation.model.NoteUiModel


fun Note.toUIModel(): NoteUiModel = NoteUiModel(id, title, content, imageLink,refLinks,createdAt.toFormattedDate())

//fun NoteUiModel.toDomain(): Note = Note(id, title, content, imageLink,refLinks)