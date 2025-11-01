package com.jpn.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "pwd")
data class PwdEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Expose
    var fieldLabel: String,
    @Expose
    var secureLabel: String,
    @Expose
    var fieldValue: String,
    @Expose
    var secureValue: String,
    @Expose
    var others: List<String>,
    @Expose
    var fieldLock: Boolean,
    var secureLock: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)