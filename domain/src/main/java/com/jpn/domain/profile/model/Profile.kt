package com.jpn.domain.profile.model

data class Profile(
    val id: Int = 0,
    val name: String,
    val jobTitle: String,
    val email: String,
    val phone: String,
    val location: String,
    val summary: String,
    val skills: String,
    val experience: String
){
    companion object {
        fun empty() = Profile(
            id = 0,
            name = "",
            jobTitle = "",
            email = "",
            phone = "",
            location = "",
            summary = "",
            skills="",
            experience = ""
        )
    }
}