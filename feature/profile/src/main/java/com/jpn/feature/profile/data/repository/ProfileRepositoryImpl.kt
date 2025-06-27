package com.jpn.feature.profile.data.repository

import com.jpn.domain.profile.model.Profile
import com.jpn.domain.profile.repository.ProfileRepository

class ProfileRepositoryImpl (
) : ProfileRepository {

    override suspend fun getProfile(): Profile {
        return Profile.empty().copy( name="Jithish P N ", jobTitle = "Mobile Lead | Android Architect", summary = "Experienced Android Developer with expertise in Jetpack Compose, Kotlin, Java, MVVM, and Clean Architecture. " +
                "Skilled in building high-quality, scalable applications, integrating REST APIs, Room, and third-party SDKs. Strong in " +
                "modularization, dependency injection (Hilt), and state management (Coroutines, Flows). Experienced in debugging, " +
                "code reviews, Agile development and team collaboration. Passionate about writing clean, maintainable code and " +
                "delivering a smooth user experience.", email = "pnjithish@gmail.com", phone = "+918075784826 / +918129895648", location = "Bangalore" , skills = "Android SDK, Android Application, Android Studio, Architecture: Clean, Modularization , Gradle, Kotlin, Jetpack " +
                "Compose, Material Design ( Recycler View , Card View) , JSON, XML, Firebase , Social Network APIs, Google Map" +
                "Integration, Billing Library for In-App Purchase, Design patterns (MVC,MVVM), Network libraries like Retrofit, Rest " +
                "APIs, Offline Storage (SQLite, Room, Realm) , Databinding , Coroutines, Flows and Custom views, Samsung Health, " +
                "Google Fit & Health Connect Integration. Others (PDF rendering, FFMPEG)")
    }

   /* override suspend fun updateProfile(profile: Profile) {
        dao.insertOrUpdate(profile.toEntity())
    }*/
}
/*
class ProfileRepositoryImpl @Inject constructor(
    private val dao: ProfileDao
) : ProfileRepository {

    override suspend fun getProfile(): Profile {
        return dao.getProfile().toDomain()
    }

    override suspend fun updateProfile(profile: Profile) {
        dao.insertOrUpdate(profile.toEntity())
    }
}
*/
