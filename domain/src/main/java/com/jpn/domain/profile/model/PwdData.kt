package com.jpn.domain.profile.model

data class PwdData(
    var id: Int,
    var key: String,
    var value: String,
    var others: List<String>,
    var createdAt: Long,
    var updatedAt: Long
)