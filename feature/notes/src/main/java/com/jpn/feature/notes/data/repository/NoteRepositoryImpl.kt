package com.jpn.feature.favorites.data.repository

import com.jpn.core.local.dao.NoteDao
import com.jpn.domain.profile.model.Note
import com.jpn.domain.profile.repository.NoteRepository
import com.jpn.feature.favorites.data.mapper.toDomain
import com.jpn.feature.favorites.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> =
        dao.getAllNotes().map { list -> list.map { it.toDomain() } }

    override suspend fun addNote(note: Note) {
        dao.insertNote(note.toEntity())
    }
}