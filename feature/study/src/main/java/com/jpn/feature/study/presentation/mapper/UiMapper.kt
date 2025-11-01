package com.jpn.feature.study.presentation.mapper

import com.jpn.domain.model.StudyNote
import com.jpn.feature.study.presentation.model.StudyNoteUiModel

fun StudyNote.toUiModel() =
    StudyNoteUiModel(
        id = id,
        title = name,
        desc = desc
    )

