package com.jpn.domain.profile.usecase

import com.jpn.domain.profile.model.Note
import com.jpn.domain.profile.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val repo: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> = repo.getNotes()
}
