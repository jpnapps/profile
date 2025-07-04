package com.jpn.feature.notes.presentation.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.domain.profile.model.Note
import com.jpn.domain.profile.usecase.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    /*    private val _title = MutableStateFlow("")
        val title: StateFlow<String> = _title

        private val _content = MutableStateFlow("")
        val content: StateFlow<String> = _content

        var title by mutableStateOf("")
        var content by mutableStateOf("")
        var imageLink by mutableStateOf("")
        var refLinks = mutableStateListOf<String>()*//*   fun onTitleChanged(newTitle: String) {
           _title.value = newTitle
       }

       fun onContentChanged(newContent: String) {
           _content.value = newContent
       }
   */

    var formState by mutableStateOf(AddNoteFormState())
        private set

    private val _uiState = MutableStateFlow<AddNoteUiState>(AddNoteUiState.Idle)
    val uiState: StateFlow<AddNoteUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<AddNoteEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onTitleChange(newValue: String) {
        formState = formState.copy(title = newValue)
    }

    fun onContentChange(newValue: String) {
        formState = formState.copy(content = newValue)
    }

    fun onImageLinkChange(newValue: String) {
        formState = formState.copy(imageLink = newValue)
    }

    fun addRefLink(link: String) {
        if (link.isBlank()) {
            formState = formState.copy(linkError = "Link cannot be empty")
            return
        }
        if (formState.refLinks.contains(link)) {
            formState = formState.copy(linkError = "Link already added")
            return
        }
        val updatedLinks = formState.refLinks.toMutableList().apply { add(link) }
        formState = formState.copy(refLinks = updatedLinks)
    }

    fun removeRefLink(index: Int) {
        val updatedLinks = formState.refLinks.toMutableList().apply { removeAt(index) }
        formState = formState.copy(refLinks = updatedLinks)
    }
    fun removeRefLink(link: String) {
        val updatedLinks = formState.refLinks.toMutableList().apply { remove(link) }
        formState = formState.copy(refLinks = updatedLinks)
    }

    fun onSaveClick() {
        if (validateForm()) {
            saveNote()
        }
    }

    fun validateForm(): Boolean {
        var valid = true
        var titleError: String? = null
        var contentError: String? = null

        if (formState.title.isBlank()) {
            titleError = "Title is required"
            valid = false
        }
        if (formState.content.isBlank()) {
            contentError = "Content is required"
            valid = false
        }

        formState = formState.copy(
            titleError = titleError,
            contentError = contentError
        )

        return valid
    }

    fun saveNote() {
        val note = Note(
            id = 0,
            title = formState.title,
            content = formState.content,
            imageLink = formState.imageLink.takeIf { it.isNotBlank() },
            refLinks = formState.refLinks,
            createdAt = System.currentTimeMillis()
        )
        viewModelScope.launch {
            _uiState.value = AddNoteUiState.Loading
            try {
                addNoteUseCase(note)
                _uiState.value = AddNoteUiState.Idle
                delay(300) // for smoother transition
                _eventFlow.emit(AddNoteEvent.SaveSuccess)
            } catch (e: Exception) {
                _uiState.value = AddNoteUiState.Loading
                _eventFlow.emit(AddNoteEvent.Error(e.message ?: "Unknown error"))
            }
        }
    }
}


sealed class AddNoteEvent {
    object SaveSuccess : AddNoteEvent()
    data class Error(val message: String) : AddNoteEvent()
    object NoNetwork : AddNoteEvent()
}

sealed class AddNoteUiState {
    object Idle : AddNoteUiState()
    object Loading : AddNoteUiState()
}