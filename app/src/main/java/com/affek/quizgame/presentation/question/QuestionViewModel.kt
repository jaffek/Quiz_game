package com.affek.quizgame.presentation.question

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.affek.quizgame.navigation.QuestionScreenNavArgs
import com.affek.quizgame.presentation.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val navArgs: QuestionScreenNavArgs = savedStateHandle.navArgs()
}