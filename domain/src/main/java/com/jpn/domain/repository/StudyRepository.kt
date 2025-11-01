package com.jpn.domain.repository

import com.jpn.domain.model.StudyNote
import kotlinx.coroutines.flow.Flow

interface StudyRepository {
    fun getStudyNotesList(): Flow<List<StudyNote>>
}
