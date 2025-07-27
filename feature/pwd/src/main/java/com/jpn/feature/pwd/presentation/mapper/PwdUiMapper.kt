package com.jpn.feature.pwd.data.mapper

import com.jpn.core.utils.extensions.toFormattedDate
import com.jpn.domain.profile.model.PwdData
import com.jpn.feature.pwd.presentation.add.AddPwdFormState
import com.jpn.feature.pwd.presentation.model.PwdUiModel

fun PwdData.toUiModel() =
    PwdUiModel(id, if(fieldLock)"xxxxxxxxxxxx" else fieldValue, secureValue, others, createdAt.toFormattedDate(), updatedAt.toFormattedDate())

fun PwdData.toForm(title: String) =
    AddPwdFormState(
        id,
        fieldLabel = fieldLabel,
        fieldValue = fieldValue,
        secureLabel= secureLabel,
        secureValue = secureValue,
        others = others,
        title = title,
        fieldLock = fieldLock,
        secureLock = secureLock
    )

fun AddPwdFormState.toPwdData(id:Int): PwdData {
    val now = System.currentTimeMillis()
    val item =
        PwdData(
            id=id,
            fieldLabel = fieldLabel,
            fieldValue = fieldValue,
            secureLabel = secureLabel,
            secureValue = secureValue,
            others = others,
            fieldLock = fieldLock,
            secureLock = secureLock,
            createdAt = now,
            updatedAt = now
        )
    return item
}