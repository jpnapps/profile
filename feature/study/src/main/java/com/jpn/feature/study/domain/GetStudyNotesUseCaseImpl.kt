package com.jpn.feature.study.domain

import com.jpn.domain.model.StudyNote
import com.jpn.domain.usecase.study.GetStudyNotesUseCase
import com.jpn.feature.study.data.repository.StudyRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStudyNotesUseCaseImpl @Inject constructor(
    private val repositoryImpl: StudyRepositoryImpl
) : GetStudyNotesUseCase {
    override fun invoke(): Flow<List<StudyNote>> {
        return repositoryImpl.getStudyNotesList()
    }
}
