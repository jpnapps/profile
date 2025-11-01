package com.jpn.domain.model

data class StudyNote(
    val id: Long = 0L,
    val name: String,
    val desc:String,
    val detail:String,
    val date: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
