package com.jpn.domain.usecase.study

import com.jpn.domain.model.StudyNote
import kotlinx.coroutines.flow.Flow

interface GetStudyNotesUseCase {
    operator fun invoke(): Flow<List<StudyNote>>
}
