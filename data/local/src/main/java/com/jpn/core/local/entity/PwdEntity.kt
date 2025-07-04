package com.jpn.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pwd")
data class PwdEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val key: String,
    val value: String,
    var others: List<String>,
    val createdAt: Long,
    val updatedAt: Long
)