package com.jpn.feature.pwd.presentation.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.utils.constants.Common
import com.jpn.core.utils.managers.ResourceManager
import com.jpn.domain.usecase.AddPwdUseCase
import com.jpn.feature.pwd.R
import com.jpn.feature.pwd.presentation.mapper.toPwdData
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
class AddPwdViewModelOld @Inject constructor(
    private val addPwdUseCase: AddPwdUseCase,
    private val resourceManager: ResourceManager
) : ViewModel() {

    var formState by mutableStateOf(AddPwdFormState(id = 0))
        private set
/*    private val _formState = MutableStateFlow(AddPwdFormState())
    val formState: StateFlow<AddPwdFormState> = _formState.asStateFlow()*/

    private val _uiState = MutableStateFlow<AddPwdUiState>(AddPwdUiState.Idle)
    val uiState: StateFlow<AddPwdUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<AddPwdEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _isAddFieldEnabled = MutableStateFlow(false)
    val isAddFieldEnabled: StateFlow<Boolean> = _isAddFieldEnabled.asStateFlow()
    private val _isAddSecureEnabled = MutableStateFlow(false)
    val isAddSecureEnabled: StateFlow<Boolean> = _isAddSecureEnabled.asStateFlow()
    private val _isSaveEnabled = MutableStateFlow(false)
    val isSaveEnabled: StateFlow<Boolean> = _isSaveEnabled.asStateFlow()


    // Field-level StateFlows for fine-grained recomposition
 /*   val label: StateFlow<String> = _formState
        .map { it.label }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val password: StateFlow<String> = _formState
        .map { it.password }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")*/


    init {
        formState = formState.copy(
            title = resourceManager.getString(R.string.add_pwd_title),
            fieldLabel = resourceManager.getString(R.string.pwd_field_label),
            secureLabel = resourceManager.getString(R.string.pwd_password_label)
        ) // mapping here

    }

    fun onFieldLabelChange(newValue: String) {
        formState = formState.copy(fieldLabel = newValue)
    }

    fun onFieldValueChange(newValue: String) {
        formState = formState.copy(fieldValue = newValue)
    }

    fun onFieldDelete(newValue: Boolean) {
        formState = formState.copy(isFieldDeleted = newValue, fieldValue = Common.EMPTY_STRING, fieldLabel = resourceManager.getString(R.string.pwd_field_label),)
        _isAddFieldEnabled.value = newValue
    }

    fun onSecureLabelChange(newValue: String) {
        formState = formState.copy(secureLabel = newValue)
    }

    fun onSecureValueChange(newValue: String) {
        formState = formState.copy(secureValue = newValue)
    }

    fun onSecureDelete(newValue: Boolean) {
        formState = formState.copy(isSecureDeleted = newValue, secureValue = Common.EMPTY_STRING,secureLabel = resourceManager.getString(R.string.pwd_password_label))
        _isAddSecureEnabled.value = newValue
    }

    fun toggleFieldLock() {
        formState = formState.copy(fieldLock = !formState.fieldLock)
    }

    fun toggleSecureLock() {
        formState = formState.copy(fieldLock = !formState.secureLock)
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

    fun removeOther(other: String) {
        val updatedLinks = formState.others.toMutableList().apply { remove(other) }
        formState = formState.copy(others = updatedLinks)
    }

    fun onSaveClick() {
        viewModelScope.launch {
            if (validateForm()) {
                savePwd()
            }
        }
    }

    suspend fun validateForm(): Boolean {
        var isValid = true
         if (formState.isFieldDeleted && formState.isSecureDeleted) {
             _eventFlow.emit(AddPwdEvent.Error("Should be have one value"))
            isValid = false
        }

        val errorFieldLabel = if (!formState.isFieldDeleted && formState.fieldLabel.isBlank()) {
            isValid = false
            resourceManager.getString(R.string.error_label_required)
        } else null

        val errorFieldValue = if (!formState.isFieldDeleted && formState.fieldValue.isBlank()) {
            isValid = false
            resourceManager.getString(R.string.error_value_required, formState.fieldLabel)
        } else null

        val errorSecureLabel = if (!formState.isSecureDeleted && formState.secureLabel.isBlank()) {
            isValid = false
            resourceManager.getString(R.string.error_label_required)
        } else null

        val errorSecureValue = if (!formState.isSecureDeleted && formState.secureValue.isBlank()) {
            isValid = false
            resourceManager.getString(R.string.error_value_required, formState.secureLabel)
        } else null

        formState = formState.copy(
            errorFieldLabel = errorFieldLabel,
            errorFieldValue = errorFieldValue,
            errorSecureLabel = errorSecureLabel,
            errorSecureValue = errorSecureValue
        )

        return isValid
    }

    fun savePwd() {
        val item = formState.toPwdData(0)
        viewModelScope.launch {
            _uiState.value = AddPwdUiState.Loading
            try {
                addPwdUseCase(item)
                _uiState.value = AddPwdUiState.Idle
                delay(300)
                _eventFlow.emit(AddPwdEvent.SaveSuccess)
            } catch (e: Exception) {
                _uiState.value = AddPwdUiState.Loading
                _eventFlow.emit(AddPwdEvent.Error(e.message ?: "Unknown error"))
            }
        }
    }
}

/*

sealed class AddPwdEvent {
    object SaveSuccess : AddPwdEvent()
    data class Error(val message: String) : AddPwdEvent()
    object NoNetwork : AddPwdEvent()
}

sealed class AddPwdUiState {
    object Idle : AddPwdUiState()
    object Loading : AddPwdUiState()
}*/
