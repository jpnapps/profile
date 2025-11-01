package com.jpn.feature.routine.presentation.dash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpn.core.ui.events.Event
import com.jpn.core.ui.events.UiEvent
import com.jpn.core.ui.states.UiState
import com.jpn.core.utils.constants.Digits
import com.jpn.core.utils.managers.LogManager
import com.jpn.core.utils.managers.ResourceManager
import com.jpn.domain.model.Routine
import com.jpn.domain.model.RoutineSubType
import com.jpn.domain.model.RoutineType
import com.jpn.domain.usecase.routine.AddRoutineUseCase
import com.jpn.domain.usecase.routine.GetTodayRoutinesByTypeUseCase
import com.jpn.domain.usecase.routine.UpdateRoutineUseCase
import com.jpn.feature.routine.presentation.mapper.toUiModel
import com.jpn.feature.routine.presentation.model.RoutineUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class RoutinesViewModel @Inject constructor(
    private val getTodayRoutinesByTypeUseCase: GetTodayRoutinesByTypeUseCase,
    private val addRoutineUseCase: AddRoutineUseCase,
    private val updateRoutineUsecase: UpdateRoutineUseCase,
    private val resourceManager: ResourceManager,
    private val logManager: LogManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<RoutineUiModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<RoutineUiModel>>> = _uiState

    private val _healthRoutinesUiState =
        MutableStateFlow<UiState<List<RoutineUiModel>>>(UiState.Loading)
    val healthRoutinesUiState: StateFlow<UiState<List<RoutineUiModel>>> = _healthRoutinesUiState

    private val _studyRoutinesUiState =
        MutableStateFlow<UiState<List<RoutineUiModel>>>(UiState.Loading)
    val studyRoutinesUiState: StateFlow<UiState<List<RoutineUiModel>>> = _studyRoutinesUiState

    private val _personalRoutinesUiState =
        MutableStateFlow<UiState<List<RoutineUiModel>>>(UiState.Loading)
    val personalRoutinesUiState: StateFlow<UiState<List<RoutineUiModel>>> = _personalRoutinesUiState

    private val _uiEvent = MutableSharedFlow<Event<UiEvent>>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun loadRoutines() {
        loadTodayRoutinesByType(RoutineType.HEALTH)
       // loadTodayRoutinesByType(RoutineType.STUDY)
        //loadTodayRoutinesByType(RoutineType.PERSONAL)
    }

    fun loadTodayRoutinesByType(type: RoutineType) {
        logManager.d(" RVM fetchHealthRoutines")
        val targetStateFlow = when (type) {
            RoutineType.HEALTH -> _healthRoutinesUiState
            RoutineType.STUDY -> _studyRoutinesUiState
            RoutineType.PERSONAL -> _personalRoutinesUiState
            else -> _personalRoutinesUiState
        }

        viewModelScope.launch {
            getTodayRoutinesByTypeUseCase(type) // Flow<List<Routine>>
                .onStart { targetStateFlow.value = UiState.Loading }
                .catch { e ->
                    logManager.e("RVM: error loading $type", e)
                    targetStateFlow.value = UiState.Error("Failed: ${e.message ?: "Unknown error"}")
                }
                .collect { routines ->
                    if (routines.isEmpty()) {
                        // ⚡ No temp list, no recursion:
                        // Insert defaults; DAO Flow will emit again automatically.
                        ensureDefaultsFor(type)
                        // keep UI in Loading while we seed defaults
                        return@collect
                    }
                    targetStateFlow.value = UiState.Success(routines.map { it.toUiModel() })
                }
        }

        /*  viewModelScope.launch {
              targetStateFlow.value = UiState.Loading
              getTodayRoutinesByTypeUseCase(type)
                  .map { list -> list.map { it.toUiModel() } }
                  .catch { e ->
                      targetStateFlow.value = UiState.Error("Failed: ${e.message}")
                  }
                  .collect { routines ->
                      targetStateFlow.value = UiState.Success(routines)
                  }
          }*/
    }


    private suspend fun ensureDefaultsFor(type: RoutineType) {
        val today = todayStartMillis()

        when (type) {
            RoutineType.HEALTH -> {
                addRoutineIfMissing(
                    name = resourceManager.getString(com.jpn.core.ui.R.string.water),
                    type = RoutineType.HEALTH,
                    sub = RoutineSubType.WATER,
                    goal = Digits.TWELVE,
                    progress = Digits.ZERO,
                    step = Digits.ONE,
                    date = today
                )
                addRoutineIfMissing(
                    name = resourceManager.getString(com.jpn.core.ui.R.string.exercise),
                    type = RoutineType.HEALTH,
                    sub = RoutineSubType.EXERCISE,
                    goal = Digits.THIRTY,
                    progress = Digits.ZERO,
                    step = Digits.FIVE,
                    date = today
                )
            }

            RoutineType.STUDY -> {
                addRoutineIfMissing(
                    name = resourceManager.getString(com.jpn.core.ui.R.string.theory),
                    type = RoutineType.STUDY,
                    sub = RoutineSubType.THEORY,
                    goal = Digits.SIXTY,
                    progress = Digits.ZERO,
                    step = Digits.FIVE,
                    date = today
                )
                addRoutineIfMissing(
                    name = resourceManager.getString(com.jpn.core.ui.R.string.programs),
                    type = RoutineType.STUDY,
                    sub = RoutineSubType.PROGRAM,
                    goal = Digits.TWO, // e.g., tasks
                    progress = Digits.ZERO,
                    step = Digits.ONE,
                    date = today
                )
                addRoutineIfMissing(
                    name = resourceManager.getString(com.jpn.core.ui.R.string.project),
                    type = RoutineType.STUDY,
                    sub = RoutineSubType.PROJECT,
                    goal = Digits.THIRTY,
                    progress = Digits.ZERO,
                    step = Digits.FIVE,
                    date = today
                )
            }

            RoutineType.PERSONAL -> {
                addRoutineIfMissing(
                    name = resourceManager.getString(com.jpn.core.ui.R.string.personal),
                    type = RoutineType.PERSONAL,
                    sub = RoutineSubType.CUSTOM,
                    goal = Digits.ONE,
                    progress = Digits.ZERO,
                    step = Digits.FIVE,
                    date = today
                )
            }
        }
    }

    private suspend fun addRoutineIfMissing(
        name: String,
        type: RoutineType,
        sub: RoutineSubType,
        goal: Int,
        progress: Int,
        step: Int,
        date: Long
    ) {
        // Assumes DB has UNIQUE index on (type, subType, date) to hard-guarantee no duplicates.
        // If you don’t have it, this still won’t duplicate because we only seed when the list is empty.
        addRoutineUseCase(
            Routine(
                id = Digits.ZERO_LONG,
                name = name,
                type = type,
                subType = sub,
                progress = progress,
                goal = goal,
                step = step,
                date = date,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    private fun todayStartMillis(): Long {
        val calendar = Calendar.getInstance() // todayStartMillis
        calendar.set(Calendar.HOUR_OF_DAY, Digits.ZERO)
        calendar.set(Calendar.MINUTE, Digits.ZERO)
        calendar.set(Calendar.SECOND, Digits.ZERO)
        calendar.set(Calendar.MILLISECOND, Digits.ZERO)
        return calendar.timeInMillis
    }
}
/*
    fun getRoutinesByType(type: RoutineType): StateFlow<List<RoutineUiModel>> =
    uiState.map { state ->
        if (state is UiState.Success) state.data.filter { it.type == type } else emptyList()
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
*/