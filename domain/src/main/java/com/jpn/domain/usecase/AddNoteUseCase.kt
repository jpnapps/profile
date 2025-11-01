package com.jpn.domain.usecase

import com.jpn.domain.model.Note
import com.jpn.domain.repository.NoteRepository

class AddNoteUseCase(private val repo: NoteRepository) {
    suspend operator fun invoke(note: Note) = repo.addNote(note)
}
