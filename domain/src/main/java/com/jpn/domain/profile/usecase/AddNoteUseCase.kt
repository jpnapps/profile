package com.jpn.domain.profile.usecase

import com.jpn.domain.profile.model.Note
import com.jpn.domain.profile.repository.NoteRepository

class AddNoteUseCase(private val repo: NoteRepository) {
    suspend operator fun invoke(note: Note) = repo.addNote(note)
}
