package com.jpn.feature.pwd.presentation.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.utils.managers.LogManager
import com.jpn.core.utils.managers.ResourceManager
import com.jpn.domain.profile.model.PwdData
import com.jpn.domain.profile.usecase.GetPwdByIdUseCase
import com.jpn.domain.profile.usecase.UpdatePwdUseCase
import com.jpn.feature.pwd.R
import com.jpn.feature.pwd.data.mapper.toForm
import com.jpn.feature.pwd.data.mapper.toPwdData
import com.jpn.feature.pwd.presentation.add.AddPwdEvent
import com.jpn.feature.pwd.presentation.add.AddPwdFormState
import com.jpn.feature.pwd.presentation.add.AddPwdUiState
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
class EditPwdViewModel @Inject constructor(
    private val getPwdByIdUseCase: GetPwdByIdUseCase,
    private val updatePwdUseCase: UpdatePwdUseCase,
    private val resourceManager: ResourceManager,
    private val logManager: LogManager
) : ViewModel() {


    private val _pwd = MutableStateFlow<PwdData?>(null)
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
                logManager.d("EPDVM loadPwd id: $id pwdData ",it)
                formState = it.toForm(resourceManager.getString(R.string.edit_pwd_title)) // mapping here
                logManager.d("EPDVM loadPwd formState ",formState)
            }
        }
    }

    fun onFieldLabelChange(newValue: String) {
        formState = formState.copy(fieldLabel = newValue)
    }

    fun onFieldValueChange(newValue: String) {
        formState = formState.copy(fieldValue = newValue)
    }

    fun onFieldDelete(newValue: Boolean) {
        formState = formState.copy(isFieldDeleted = newValue)
    }


    fun onSecureLabelChange(newValue: String) {
        formState = formState.copy(secureLabel = newValue)
    }

    fun onSecureValueChange(newValue: String) {
        formState = formState.copy(secureValue = newValue)
    }

    fun onSecureDelete(newValue: Boolean) {
        formState = formState.copy(isSecureDeleted = newValue)
    }

    fun toggleFieldLock() {
        formState = formState.copy(fieldLock = !formState.fieldLock)
    }

    fun toggleSecureLock() {
        formState = formState.copy(fieldLock = !formState.secureLock)
    }

    fun addOther(link: String) {
        if (link.isBlank()) {
            formState = formState.copy(linkError = resourceManager.getString(R.string.error_others_not_be_empty))
            return
        }
        if (formState.others.contains(link)) {
            formState = formState.copy(linkError = resourceManager.getString(R.string.error_other_already_added))
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
            updatePwd()
        }
    }

    fun validateForm(): Boolean {
        var isValid = true

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

    fun updatePwd() {
        val item =formState.toPwdData(formState.id ?: 0)
        viewModelScope.launch {
            _uiState.value = AddPwdUiState.Loading
            try {
                updatePwdUseCase(item)
                _uiState.value = AddPwdUiState.Idle
                delay(300) // for smoother transition
                _eventFlow.emit(AddPwdEvent.SaveSuccess)
            } catch (e: Exception) {
                _uiState.value = AddPwdUiState.Loading
                _eventFlow.emit(AddPwdEvent.Error(e.message ?: resourceManager.getString(R.string.error_unknown)))
            }
        }
    }
}
