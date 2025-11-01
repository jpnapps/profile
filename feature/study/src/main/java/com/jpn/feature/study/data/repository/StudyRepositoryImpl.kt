package com.jpn.feature.study.data.repository

import com.google.gson.Gson
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.model.StudyNote
import com.jpn.domain.repository.StudyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StudyRepositoryImpl @Inject constructor(
    private val logManager: LogManager,
    private val gson: Gson
) : StudyRepository {

    override fun getStudyNotesList(): Flow<List<StudyNote>> {
        return flow {
            emit(getStudyNotes())
        }
    }

    fun getStudyNotes() = listOf(
        StudyNote(
            1,
            "SideEffect in Compose",
            "any operation that causes changes outside the composable function \n\n" +
                    "SideEffect → Composable → External effect ,runs after every recomposition; lightweight external/logging updates\n SideEffect { analytics.logScreen(\"Home\") }" +
                    "\n\n" +
                    "LaunchedEffect → Composable → Coroutine ,runs suspend/coroutines; run on key change or one off, eg:Loading data\n LaunchedEffect(userId) { viewModel.loadData(userId) }" +
                    "\n\n" +
                    "DisposableEffect → Composable → Setup & Cleanup ; e.g., register/unregister listeners,sensors, callbacks\n DisposableEffect(sensorManager) { val l = listener; onDispose { sensorManager.unregisterListener(l) } }" +
                    "\n\n" +
                    "rememberCoroutineScope → Composable → Coroutine, on user demand,anywhere in composable, launch coroutines safely  eg: button clicks,swipe refresh, event-driven tasks.\n val scope = rememberCoroutineScope(); scope.launch { doWork() }" +
                    "\n\n" +
                    "ProduceState → Suspend → Compose State ,produce state from suspend/async work, updates UI state automatically Eg: Load user profile from network or DB, auto-recompose \n val profile = produceState<User?>(null) { value = api.getUserProfile() }" +
                    "\n\n" +
                    "rememberUpdatedState → State → Lambda safe reference, Fix Stale capture, keep latest value in lambdas/callbacks \n val latest = rememberUpdatedState(newValue); callback { doSomething(latest.value) } " +
                    "\n\n" +
                    "SnapshotFlow → Compose State → Flow ,observe Compose state as Flow, emit changes externally e.g: Debounced search, scroll position saving\n snapshotFlow { query }.debounce(300).collect { viewModel.search(it) }" +
                    "\n\n" +
                    "Rule: UI state via State; side effects for everything external or async" + "\n\n Quick Mnemonic for Interview\n" +
                    "\n" +
                    "“L-P-D-S-R-S” → LaunchedEffect, ProduceState, DisposableEffect, SideEffect, rememberCoroutineScope, SnapshotFlow\n" +
                    "\n" +
                    "Think trigger → produce → cleanup → light effect → manual coroutine → observe",
            ""
        ),
        StudyNote(
            2,
            "Stale capture in Compose.",
            "A coroutine or lambda captures a variable at start. If the variable changes later, the coroutine still uses the old value → stale capture.",
            ""
        ),
        StudyNote(
            3,
            "Event<T> wrapper",
            "That Event<T> wrapper exists because LiveData re-emits values on configuration change. So when you rotated the screen, an old event (like “show dialog”) would re-fire.\n" +
                    "→ The wrapper ensured that the UI consumed it only once.\n" +
                    "\n" +
                    "Compose doesn’t behave like that.\n" +
                    "\n" +
                    "StateFlow / SharedFlow don’t re-emit old values unless you tell them to (by replay > 0",
            ""
        ),
        StudyNote(4, "Biology Cells", "Cell Structure & Functions", ""),
        StudyNote(5, "History Ancient", "Egyptian Civilization", ""),
        StudyNote(6, "Geography Maps", "World Maps & Continents", ""),
        StudyNote(7, "English Grammar", "Tenses & Sentence Structure", ""),
        StudyNote(8, "Computer Science", "Introduction to Kotlin", ""),
        StudyNote(9, "Economics Basics", "Supply & Demand", ""),
        StudyNote(10, "Art & Design", "Color Theory and Composition", ""),
    )

}