package com.jpn.feature.routine.presentation.dash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.jpn.core.ui.cards.StepGoalCard
import com.jpn.core.ui.containers.LoadingScreen
import com.jpn.core.ui.states.UiState
import com.jpn.core.ui.tabs.TabItem
import com.jpn.core.ui.tabs.TabsRow
import com.jpn.core.designsystem.theme.AppTheme
import com.jpn.domain.model.RoutineSubType
import com.jpn.feature.routine.presentation.model.RoutineUiModel

sealed class RoutineTab(
    override val title: String,
    override val icon: ImageVector
) : TabItem {

    object Health : RoutineTab("Health", Icons.Default.Favorite)
    object Study : RoutineTab("Study", Icons.Default.Star)
    object Personal : RoutineTab("Personal", Icons.Default.Person)
}

@Composable
fun RoutineScreen(
    viewModel: RoutinesViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val tabs = listOf(RoutineTab.Health, RoutineTab.Study, RoutineTab.Personal)
    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.loadRoutines()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Tabs
        TabsRow(tabs, selectedIndex = selectedTabIndex) { select -> selectedTabIndex = select }
        // Tab Content
        when (selectedTabIndex) {
            0 -> HealthTabContent(viewModel)
            1 -> StudyTabContent(viewModel)
            2 -> PersonalTabContent(viewModel)
        }
    }

}

@Composable
fun HealthTabContent(viewModel: RoutinesViewModel) {
    val state by viewModel.healthRoutinesUiState.collectAsState()
    when (state) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> Text("⚠ ${(state as UiState.Error).message}")
        is UiState.Success -> {
            val routines = (state as UiState.Success<List<RoutineUiModel>>).data
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                routines.forEach { routine ->
                    StepGoalCard(
                        title = routine.name,
                        currentValue = routine.progress,
                        unit = when (routine.subType) {
                            RoutineSubType.WATER -> "Glass"
                            RoutineSubType.EXERCISE -> "Min"
                            else -> ""
                        },
                        goal = routine.goal,
                        step = routine.step,
                        max = routine.goal * 3, // example scaling
                        onValueChange = { newValue ->
                            //viewModel.updateRoutine(routine.copy(progress = newValue))
                        },
                        lightColor = if (routine.subType == RoutineSubType.WATER)
                            AppTheme.colors.waterLight else AppTheme.colors.exerciseLight,
                        darkColor = if (routine.subType == RoutineSubType.WATER)
                            AppTheme.colors.waterDark else AppTheme.colors.exerciseDark
                    )
                }
            }
        }
    }
    /*
     var water by remember { mutableStateOf(0) }
     var exercise by remember { mutableStateOf(0) }
     Row(
         modifier = Modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceEvenly
     ) {
         StepGoalCard(
             title = "Water",
             currentValue = water,
             unit = "Glass",
             goal = 12,
             step = 1,
             max = 20,
             onValueChange = { water = it },
             lightColor = AppTheme.colors.waterLight,
             darkColor = AppTheme.colors.waterDark
         )

         StepGoalCard(
             title = "Exercise",
             currentValue = exercise,
             unit = "Min",
             goal = 30,
             step = 5,
             max = 60,
             onValueChange = { exercise = it },
             lightColor = AppTheme.colors.exerciseLight,
             darkColor = AppTheme.colors.exerciseDark
         )
     }*/
}

@Composable
fun StudyTabContent(viewModel: RoutinesViewModel) {
    var water by remember { mutableStateOf(0) }
    var exercise by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StepGoalCard(
            title = "Programs",
            currentValue = water,
            unit = "",
            goal = 2,
            step = 1,
            max = 20,
            onValueChange = { water = it },
            lightColor = AppTheme.colors.programsLight,
            darkColor = AppTheme.colors.programsDark
        )

        StepGoalCard(
            title = "Study",
            currentValue = exercise,
            unit = "Min",
            goal = 60,
            step = 20,
            max = 180,
            onValueChange = { exercise = it },
            lightColor = AppTheme.colors.studyLight,
            darkColor = AppTheme.colors.studyDark
        )
    }
}

@Composable
fun PersonalTabContent(viewModel: RoutinesViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item { Text("📝 Daily To-Do") }
        item { Text("📅 Other Tasks") }
    }
}


//    val uiState by viewModel.uiState.collectAsState()