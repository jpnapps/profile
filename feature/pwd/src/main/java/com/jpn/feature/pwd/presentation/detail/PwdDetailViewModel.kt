package com.jpn.feature.pwd.presentation.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.utils.managers.LogManager
import com.jpn.core.utils.managers.ResourceManager
import com.jpn.core.utils.managers.TimberLogManager
import com.jpn.domain.profile.usecase.GetPwdByIdUseCase
import com.jpn.domain.profile.usecase.UpdatePwdUseCase
import com.jpn.feature.pwd.R
import com.jpn.feature.pwd.data.mapper.toForm
import com.jpn.feature.pwd.presentation.add.AddPwdFormState
import com.jpn.feature.pwd.presentation.add.AddPwdUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PwdDetailViewModel @Inject constructor(
    private val getPwdByIdUseCase: GetPwdByIdUseCase,
    private val updatePwdUseCase: UpdatePwdUseCase,
    private val resourceManager: ResourceManager,
    private val logManager: LogManager
) : ViewModel() {

    var formState by mutableStateOf(AddPwdFormState())
        private set
    private val _uiState = MutableStateFlow<AddPwdUiState>(AddPwdUiState.Idle)
    val uiState: StateFlow<AddPwdUiState> = _uiState.asStateFlow()

    var isEditMode = mutableStateOf(false)
        private set

    fun loadPwd(id: Int) {
        Log.d("jpLogs", "LogManager class: ${logManager::class.simpleName}")
        logManager.d("VM PDVM Xxxxxxxxxxxxxxxxxxxx")
         viewModelScope.launch {
            getPwdByIdUseCase(id).collect {
                formState = it.toForm(resourceManager.getString(R.string.detail_title)) // mapping here
                Log.d("jpLogs", "VM PDVM  USECASE loadPwd id: $id formState "+TimberLogManager().convertJson(formState))

            }
        }
    }

}
// private var loadJob: Job? = null


/*    private val _uiState = MutableStateFlow<PwdsUiState>(PwdsUiState.Idle)
    val uiState: StateFlow<PwdsUiState> = _uiState*/

/*    val pwds: StateFlow<PwdData> = getPwdByIdUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PwdData())*/

/*    private val _pwd = MutableStateFlow<PwdData?>(null)
    val pwd: StateFlow<PwdData?> = _pwd*/
