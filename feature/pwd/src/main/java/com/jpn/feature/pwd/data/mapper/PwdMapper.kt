package com.jpn.feature.pwd.data.mapper

import com.jpn.core.local.entity.PwdEntity
import com.jpn.core.utils.constants.Common.EMPTY_STRING
import com.jpn.core.utils.extensions.toDecryptString
import com.jpn.core.utils.extensions.toEncryptString
import com.jpn.domain.model.PwdData

fun PwdEntity.toDomain(): PwdData = PwdData(
    id,
    fieldLabel = fieldLabel.takeIf { it != EMPTY_STRING }?.toDecryptString() ?: EMPTY_STRING,
    fieldValue = fieldValue.takeIf { it != EMPTY_STRING }?.toDecryptString() ?: EMPTY_STRING,
    secureLabel = secureLabel.takeIf { it != EMPTY_STRING }?.toDecryptString() ?: EMPTY_STRING,
    secureValue = secureValue.takeIf { it != EMPTY_STRING }?.toDecryptString() ?: EMPTY_STRING,
    others = others,
    fieldLock = fieldLock,
    secureLock = secureLock,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun PwdData.toEntity(): PwdEntity = PwdEntity(
    id = id,
    fieldLabel = fieldLabel.takeIf { it != EMPTY_STRING }?.toEncryptString() ?: EMPTY_STRING,
    fieldValue = fieldValue.takeIf { it != EMPTY_STRING }?.toEncryptString() ?: EMPTY_STRING,
    secureLabel = secureLabel.takeIf { it != EMPTY_STRING }?.toEncryptString() ?: EMPTY_STRING,
    secureValue = secureValue.takeIf { it != EMPTY_STRING }?.toEncryptString() ?: EMPTY_STRING,
    others = others,
    fieldLock = fieldLock,
    secureLock = secureLock,
    createdAt = createdAt,
    updatedAt = updatedAt
)

