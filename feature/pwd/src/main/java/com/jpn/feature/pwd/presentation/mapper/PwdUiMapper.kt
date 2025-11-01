package com.jpn.feature.pwd.presentation.mapper

import com.jpn.core.utils.constants.Common.EMPTY_STRING
import com.jpn.core.utils.constants.Common.XX_TEXT
import com.jpn.core.utils.extensions.toFormattedDate
import com.jpn.domain.model.PwdData
import com.jpn.feature.pwd.presentation.add.AddPwdFormState
import com.jpn.feature.pwd.presentation.model.PwdUiModel

fun PwdData.toUiModel() =
    PwdUiModel(
        id,
        // if (fieldLock && fieldValue != EMPTY_STRING) "xxxxxxxxxxxx" else fieldValue,
        fieldValue.takeIf { !(fieldLock && it.isNotBlank()) } ?: XX_TEXT,
        secureValue.takeIf { it.isNotBlank() } ?: XX_TEXT,
        others,
        createdAt.toFormattedDate(),
        updatedAt.toFormattedDate()
    )

fun PwdData.toForm(title: String) =
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
}