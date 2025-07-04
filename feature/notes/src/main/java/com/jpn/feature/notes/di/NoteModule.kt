package com.jpn.feature.notes.di

import com.jpn.core.local.dao.NoteDao
import com.jpn.domain.profile.repository.NoteRepository
import com.jpn.domain.profile.usecase.AddNoteUseCase
import com.jpn.domain.profile.usecase.GetNotesUseCase
import com.jpn.feature.favorites.data.repository.NoteRepositoryImpl
import com.jpn.feature.notes.presentation.list.NotesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {
    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDao: NoteDao
    ): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }

    @Provides
    @Singleton
    fun provideGetNotesUseCase(
        repository: NoteRepository
    ): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddNoteUseCase(
        repository: NoteRepository
    ): AddNoteUseCase {
        return AddNoteUseCase(repository)
    }

    @Provides
    fun provideNotesViewModel(
        getNoteUseCase: GetNotesUseCase,
        addNoteUseCase: AddNoteUseCase
    ): NotesViewModel {
        return NotesViewModel(getNoteUseCase, addNoteUseCase)
    }

    /* @Provides
     @Singleton
     fun provideUpdateNoteUseCase(
         repository: NoteRepository
     ): UpdateNoteUseCase {
         return UpdateNoteUseCase(repository)
     }*/
}
