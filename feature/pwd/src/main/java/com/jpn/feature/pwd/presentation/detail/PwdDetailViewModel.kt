package com.jpn.feature.pwd.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.domain.profile.model.PwdData
import com.jpn.domain.profile.usecase.GetPwdByIdUseCase
import com.jpn.domain.profile.usecase.UpdatePwdUseCase
import com.jpn.feature.favorites.data.mapper.toForm
import com.jpn.feature.notes.presentation.add.AddPwdEvent
import com.jpn.feature.notes.presentation.add.AddPwdFormState
import com.jpn.feature.notes.presentation.add.AddPwdUiState
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
class PwdDetailViewModel @Inject constructor(
    private val getPwdByIdUseCase: GetPwdByIdUseCase,
    private val updatePwdUseCase: UpdatePwdUseCase
) : ViewModel() {

/*    private val _uiState = MutableStateFlow<PwdsUiState>(PwdsUiState.Idle)
    val uiState: StateFlow<PwdsUiState> = _uiState*/

/*    val pwds: StateFlow<PwdData> = getPwdByIdUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PwdData())*/

    private val _pwd= MutableStateFlow<PwdData?>(null)
    val pwd: StateFlow<PwdData?> = _pwd


    var formState by mutableStateOf(AddPwdFormState())
        private set
    private val _uiState = MutableStateFlow<AddPwdUiState>(AddPwdUiState.Idle)
    val uiState: StateFlow<AddPwdUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<AddPwdEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var isEditMode = mutableStateOf(false)
        private set

    fun loadPwd(id: Int) {
        viewModelScope.launch {
            getPwdByIdUseCase(id).collect {
                _pwd.value = it

                formState = it.toForm() // mapping here

            }
        }
    }

    fun toggleEditMode() {
        isEditMode.value = !isEditMode.value
    }

    fun onKeyChange(newValue: String) {
        formState = formState.copy(key = newValue)
    }

    fun onValueChange(newValue: String) {
        formState = formState.copy(value = newValue)
    }

    fun addOther(link: String) {
        if (link.isBlank()) {
            formState = formState.copy(linkError = "Others cannot be empty")
            return
        }
        if (formState.others.contains(link)) {
            formState = formState.copy(linkError = "Other already added")
            return
        }
        val updatedLinks = formState.others.toMutableList().apply { add(link) }
        formState = formState.copy(others = updatedLinks)
    }

    fun removeOther(index: Int) {
        val updatedLinks = formState.others.toMutableList().apply { removeAt(index) }
        formState = formState.copy(others = updatedLinks)
    }
    fun removeOther(other: String) {
        val updatedLinks = formState.others.toMutableList().apply { remove(other) }
        formState = formState.copy(others = updatedLinks)
    }

    fun onSaveClick() {
        if (validateForm()) {
            saveNote()
        }
    }

    fun validateForm(): Boolean {
        var valid = true
        var keyError: String? = null
        var valueError: String? = null

        if (formState.key.isBlank()) {
            keyError = "Key is required"
            valid = false
        }
        if (formState.value.isBlank()) {
            valueError = "Value is required"
            valid = false
        }

        formState = formState.copy(
            keyError = keyError,
            valueError = valueError
        )

        return valid
    }

    fun saveNote() {
        val item = PwdData(
            id = formState.id?:0,
            key = formState.key,
            value = formState.value,
            others = formState.others,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        viewModelScope.launch {
            _uiState.value = AddPwdUiState.Loading
            try {
                updatePwdUseCase(item)
                _uiState.value = AddPwdUiState.Idle
                delay(300) // for smoother transition
                _eventFlow.emit(AddPwdEvent.SaveSuccess)
            } catch (e: Exception) {
                _uiState.value = AddPwdUiState.Loading
                _eventFlow.emit(AddPwdEvent.Error(e.message ?: "Unknown error"))
            }
        }
    }
}
