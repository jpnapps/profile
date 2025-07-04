package com.jpn.feature.favorites.data.mapper

import com.jpn.core.local.entity.NoteEntity
import com.jpn.domain.profile.model.Note

//fun NoteDto.toDomain(): Note = Note(id, title, content, timestamp)

fun NoteEntity.toDomain(): Note = Note(id, title, content, imageLink,refLinks,createdAt)

fun Note.toEntity(): NoteEntity = NoteEntity( title = title, content =  content, imageLink = imageLink, refLinks = refLinks, createdAt = createdAt)

