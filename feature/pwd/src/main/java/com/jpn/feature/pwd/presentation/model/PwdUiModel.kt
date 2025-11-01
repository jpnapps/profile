package com.jpn.feature.pwd.presentation.model

data class PwdUiModel(
    val id: Int,
    val label: String,
    val secureData: String,
    val others:List<String>,
    val createdAt: String,
    val updatedAt: String,
    val xxText: String= "XXXXXXXXXXXXX",
)
