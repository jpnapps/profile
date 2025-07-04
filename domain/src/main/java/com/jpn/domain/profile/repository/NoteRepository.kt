package com.jpn.domain.profile.repository

import com.jpn.domain.profile.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(favorite: Note)
}