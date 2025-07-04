package com.jpn.domain.profile.model

data class Note(
    var id: Int,
    var title: String,
    var content: String,
    var imageLink: String?,
    var refLinks: List<String>,
    var createdAt: Long
) /*{
    companion object {
        fun empty() = Note(
            id = 0,
            title = "",
            content = "",
            imageLink = "",
            refLinks = listOf(),
            createdAt = 0L
        )
    }
}*/