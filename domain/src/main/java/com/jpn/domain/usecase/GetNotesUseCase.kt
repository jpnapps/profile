package com.jpn.domain.usecase

import com.jpn.domain.model.Note
import com.jpn.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val repo: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> = repo.getNotes()
}
