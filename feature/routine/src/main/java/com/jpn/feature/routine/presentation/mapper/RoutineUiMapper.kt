package com.jpn.feature.routine.presentation.mapper

import com.jpn.domain.model.Routine
import com.jpn.feature.routine.presentation.model.RoutineUiModel

fun Routine.toUiModel() =
    RoutineUiModel(
        id = id,
        name = name,
        type = type,
        subType = subType,
        progress = progress,
        goal = goal,
        step = step,
        date = date,
        updatedAt = updatedAt
    )

/*
fun Routine.toForm(title: String) =
    AddPwdFormState(
        id,
        fieldLabel = fieldLabel,
        fieldValue = fieldValue,
        secureLabel = secureLabel,
        secureValue = secureValue,
        others = others,
        title = title,
        fieldLock = fieldLock,
        secureLock = secureLock,
        isFieldDeleted = fieldValue.isBlank(),
        isSecureDeleted = secureValue.isBlank()
    )

fun AddPwdFormState.toPwdData(id: Int): PwdData {
    val now = System.currentTimeMillis()
    val item =
        PwdData(
            id = id,
            fieldLabel = if (!isFieldDeleted) fieldLabel else EMPTY_STRING,
            fieldValue = if (!isFieldDeleted) fieldValue else EMPTY_STRING,
            secureLabel = if (!isSecureDeleted) secureLabel else EMPTY_STRING,
            secureValue = if (!isSecureDeleted) secureValue else EMPTY_STRING,
            others = others,
            fieldLock = fieldLock,
            secureLock = secureLock,
            createdAt = now,
            updatedAt = now
        )
    return item
}*/