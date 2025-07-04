package com.jpn.feature.favorites.data.mapper

import com.jpn.core.local.entity.PwdEntity
import com.jpn.domain.profile.model.PwdData

fun PwdEntity.toDomain(): PwdData = PwdData(id, key, value, others, createdAt, updatedAt)

fun PwdData.toEntity(): PwdEntity = PwdEntity(
    id= id , key = key, value = value, others = others, createdAt = createdAt, updatedAt = updatedAt
)

