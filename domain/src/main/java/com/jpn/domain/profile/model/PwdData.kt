package com.jpn.domain.profile.model

data class PwdData(
    var id: Int,
    var fieldLabel: String,
    var secureLabel: String,
    var fieldValue: String,
    var secureValue: String,
    var others: List<String>,
    var fieldLock: Boolean,
    var secureLock: Boolean,
    var createdAt: Long,
    var updatedAt: Long
)