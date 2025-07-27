package com.jpn.feature.pwd.presentation.add

data class AddPwdFormState(
    val id: Int?=null,
    val fieldLabel: String = "",
    val fieldValue: String = "",
    val secureLabel :String ="",
    val secureValue:String ="",
    val value: String = "",
    val others: List<String> = emptyList(),
    val keyError: String? = null,
    val valueError: String? = null,
    val linkInput: String = "",
    val linkError: String? = null,
    val title: String = "",
    val fieldLock: Boolean = false,
    val isFieldDeleted: Boolean = false,
    val secureLock: Boolean = true,
    val isSecureDeleted: Boolean = false,
    val errorFieldLabel: String? = null,
    val errorFieldValue: String? = null,
    val errorSecureLabel: String? = null,
    val errorSecureValue: String? = null,
)
