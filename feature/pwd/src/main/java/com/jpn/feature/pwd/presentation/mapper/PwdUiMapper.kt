package com.jpn.feature.favorites.data.mapper

import com.jpn.core.utils.extensions.toFormattedDate
import com.jpn.domain.profile.model.PwdData
import com.jpn.feature.notes.presentation.add.AddPwdFormState
import com.jpn.feature.notes.presentation.model.PwdUiModel

fun PwdData.toUIModel() =
    PwdUiModel(id, key, value, others, createdAt.toFormattedDate(), updatedAt.toFormattedDate())
fun PwdData.toForm() =
    AddPwdFormState( id,key, value, others)