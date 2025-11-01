package com.jpn.feature.pwd.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.domain.usecase.AddPwdUseCase
import com.jpn.domain.usecase.ExportPwdsUseCase
import com.jpn.domain.usecase.GetPwdsUseCase
import com.jpn.feature.pwd.presentation.mapper.toUiModel
import com.jpn.feature.pwd.presentation.model.PwdUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PwdsViewModel @Inject constructor(
    private val getPwdsUseCase: GetPwdsUseCase,
    private val addPwdUseCase: AddPwdUseCase,
    private val exportPwdsUseCase: ExportPwdsUseCase
) : ViewModel() {

/*    private val _uiState = MutableStateFlow<PwdsUiEvent>(PwdsUiEvent.Idle)
    val uiState: StateFlow<PwdsUiEvent> = _uiState*/

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    val pwdUiModels: StateFlow<List<PwdUiModel>> = getPwdsUseCase()
        .map { list -> list.map { it.toUiModel() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    /* fun exportPwdAsJson(): String {
        return exportPwdsUseCase.invoke()
    }*/

 /*   fun exportToClipboard(context: Context) {
        viewModelScope.launch {
            val json = exportPwdsUseCase() // suspend call
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("PWD Backup", json)
            clipboard.setPrimaryClip(clip)
        }
    }*/
    fun exportPwds() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = exportPwdsUseCase()
            _uiEvent.emit(UiEvent.CopytoClip(json))
        }
    }
}

sealed class UiEvent {
    object Idle : UiEvent()
    object Loading : UiEvent()
    object Success : UiEvent()
    data class Error(val message: String) : UiEvent()
    data class CopytoClip( val json : String) : UiEvent()
    data class ShowToast(val message: String) : UiEvent()
}
