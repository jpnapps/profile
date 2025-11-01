package com.jpn.domain.repository

import com.jpn.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(favorite: Note)
}