package com.jpn.feature.profile.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.domain.profile.model.Profile
import com.jpn.domain.profile.usecase.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
 //   private val updateProfileUseCase: UpdateProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState

  /*  var profile by mutableStateOf<Profile?>(null)
        private set*/

    init {
        getProfile()
        /*viewModelScope.launch {
            profile = getProfileUseCase()
        }*/
    }

    private fun getProfile() {
        viewModelScope.launch {
            try {
                _uiState.value = ProfileUiState.Loading
                delay(3000)
                val profile = getProfileUseCase()
                _uiState.value = ProfileUiState.Success(profile)
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error("Failed: ${e.message}")
            }
        }
    }
 /*   fun updateProfile(newProfile: Profile) {
        viewModelScope.launch {
            updateProfileUseCase(newProfile)
            profile = newProfile
        }
    }*/
}


sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val profile: Profile) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}