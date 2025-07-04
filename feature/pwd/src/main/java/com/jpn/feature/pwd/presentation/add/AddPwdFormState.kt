package com.jpn.feature.notes.presentation.add

data class AddPwdFormState(
    val id: Int?=null,
    val key: String = "",
    val value: String = "",
    val others: List<String> = emptyList(),
    val keyError: String? = null,
    val valueError: String? = null,
    val linkInput: String = "",
    val linkError: String? = null
)
