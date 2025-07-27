package com.jpn.feature.pwd.data.mapper

import com.jpn.core.local.entity.PwdEntity
import com.jpn.core.utils.extensions.toDecryptString
import com.jpn.core.utils.extensions.toEncryptString
import com.jpn.domain.profile.model.PwdData

fun PwdEntity.toDomain(): PwdData = PwdData(
    id,
    fieldLabel=fieldLabel.toDecryptString(),
    fieldValue = fieldValue.toDecryptString(),
    secureLabel = secureLabel.toDecryptString(),
    secureValue = secureValue.toDecryptString(),
    others=others,
    fieldLock = fieldLock,
    secureLock = secureLock,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun PwdData.toEntity(): PwdEntity = PwdEntity(
    id = id,
    fieldLabel = fieldLabel.toEncryptString(),
    fieldValue = fieldValue.toEncryptString(),
    secureLabel = secureLabel.toEncryptString(),
    secureValue = secureValue.toEncryptString(),
    others = others,
    fieldLock = fieldLock,
    secureLock = secureLock,
    createdAt = createdAt,
    updatedAt = updatedAt
)

