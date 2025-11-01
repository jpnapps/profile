package com.jpn.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "routine")
data class RoutineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    @Expose
    var type: Int,
    @Expose
    var subType: Int,
    @Expose
    var progress: Int,
    @Expose
    var step: Int,
    @Expose
    var goal: Int,
    @Expose
    val date: Long,
    val updatedAt: Long = System.currentTimeMillis()
)
