package com.jpn.feature.study.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudyAddScreenViewModel @Inject constructor(
) : ViewModel() {

    fun saveAdd(text: String): Boolean = text == "3426"
}